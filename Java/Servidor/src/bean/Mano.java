package bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import dtos.*;
import enums.TipoEnvite;


/**
 * La Mano esta compuesta por varias Bazas
**/

@Entity
@Table (name = "Manos")
public class Mano {
	@Id
	@Column (name = "id_mano", nullable = false)
	@GeneratedValue
	private int id;
	@Column (name = "nro_mano")
	private int numeroMano;
	@Transient
	private Chico chico; //se utiliza para reemplazar los observers	
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn (name = "id_mano")
	private List<Baza> bazas;
	@OneToMany (cascade = CascadeType.ALL) /* fetch = FetchType.EAGER)*/
	@JoinColumn (name = "id_mano")
	private List<CartaJugador> cartasJugador;
	@Transient
	private Envite enviteActual;
	@Transient
	private Jugador jugadorActual;
	@Transient
	private Mazo mazo;
	@Transient
	private List<Jugador> ordenJuego;

	@Transient
	private List<PuntajePareja> puntajes;
	@Transient
	private byte envidoPareja1;
	@Transient
	private byte envidoPareja2;


	public Mano() {
		
	}

	public Mano(Chico chico, int numeroMano, List<Jugador> ordenJuego, List<PuntajePareja> puntajes) {
		this.numeroMano = numeroMano;
		this.chico = chico;
		this.bazas = new ArrayList<Baza>();
		this.cartasJugador = new ArrayList<CartaJugador>();
		this.enviteActual = null;
		this.jugadorActual = ordenJuego.get(0);
		this.mazo = new Mazo();
		this.puntajes = puntajes;

		this.ordenJuego = ordenJuego;
//		this.ordenJuego = new ArrayList<Jugador>();
//		System.arraycopy(ordenJuego, 0, this.ordenJuego, 0, ordenJuego.size());

		repartirCartas(ordenJuego);

		this.bazas.add(new Baza(this, 1, ordenJuego));
	}

	private void repartirCartas(List<Jugador> ordenJuego) {
		int numeroJugador = 0;

		while(cartasJugador.size() < 12) {
			if(numeroJugador > 3)
				numeroJugador = 0;

			CartaJugador carta = new CartaJugador(ordenJuego.get(numeroJugador), mazo.obtenerCarta(), false);
			numeroJugador++;
			cartasJugador.add(carta);
		}
	}
	
	public List<Jugador> getOrdenJuego() {
		return ordenJuego;
	}
	
	

	public void setOrdenJuego(List<Jugador> ordenJuego) {
		this.ordenJuego = ordenJuego;
	}

	public void setBazas(List<Baza> bazas) {
		this.bazas = bazas;
	}

	public void setCartasJugador(List<CartaJugador> cartasJugador) {
		this.cartasJugador = cartasJugador;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroMano() {
		return numeroMano;
	}

	public void setNumeroMano(int numeroMano) {
		this.numeroMano = numeroMano;
	}

	public List<Baza> getBazas() {
		return bazas;
	}

	public void setBazas(ArrayList<Baza> bazas) {
		this.bazas = bazas;
	}

	public List<CartaJugador> getCartasJugador() {
		return cartasJugador;
	}

	public void setCartasJugador(ArrayList<CartaJugador> cartasJugador) {
		this.cartasJugador = cartasJugador;
	}

	public Envite getEnviteActual() {
		return enviteActual;
	}

	public void setEnviteActual(Envite enviteActual) {
		this.enviteActual = enviteActual;
	}

	public Mazo getMazo() {
		return mazo;
	}

	public void setMazo(Mazo mazo) {
		this.mazo = mazo;
	}

	public Pareja obtenerGanadorEnvido() {
		if (envidoPareja1 > envidoPareja2) {
			return null;
		} else if (envidoPareja1 < envidoPareja2) {
			return null;
		} else if (envidoPareja1 == envidoPareja2) {
			return obtenerParejaMano();
		}
		return null;
	}

	private Pareja obtenerParejaMano() {
		// analizo a que Pareja pertenece el jugador que es 'mano' - ordenJuego.get(0) -
		if (puntajes.get(0).getPareja().tenesJugador(ordenJuego.get(0))) {
			return puntajes.get(0).getPareja();
		}
		return puntajes.get(1).getPareja();
	}

	////////////////////////////////////
	/* NO HIZO FALTA LO HICE DE AFUERA */
	/////////////////////////////////
	public void recalcularOrdenJuego() {
		
	}

	public void nuevaBaza(int numeroBaza, List<Jugador> ordenJuego) {
		
	}

	public ManoDTO toDTO() {
		ManoDTO dto = new ManoDTO();

		dto.setId(this.id);
		dto.setNumeroMano(this.numeroMano);
		if(enviteActual!=null){
			dto.setEnviteActual((EnviteDTO) this.enviteActual.toDTO());
		}
		ArrayList<BazaDTO> bazasDto = new ArrayList<BazaDTO>();
		
		for(int i=0; i<bazas.size(); i++) {
			bazasDto.add(bazas.get(i).toDTO());
			
		}
		dto.setBazas(bazasDto);
		ArrayList<CartaJugadorDTO> cartasDto = new ArrayList<CartaJugadorDTO>();
		
		for(int i=0; i<cartasJugador.size(); i++) {
			cartasDto.add(cartasJugador.get(i).toDTO());
		}
		dto.setCartasJugador(cartasDto);

		return dto;
	}

	public boolean tocaCartaMano() {
		Baza baza = obtenerUltimaBaza(); //obtengo la ultima baza

		if(baza.obtenerGanador() == null){ //la baza no tiene un ganador, o sea sigue activa
			int cartasTiradas = 0;
			
			for (Movimiento mov: baza.getTurnosBaza()) {
				if(mov instanceof CartaTirada)
					cartasTiradas++;
			}
			
			if (cartasTiradas < 4) { //Todavia no tiraron todos sus cartas
				Movimiento mov = baza.obtenerUltimoMovimiento();
				if(mov instanceof CartaTirada)  //Lo ultimo que se tiro fue una carta, no hay que responder envite 
					return true;
				
				return false;
			} else {
				return false;
			}
		}

		return false;
	}

	public Jugador obtenerTurnoJugadorMano() {
		return obtenerUltimaBaza().obtenerTurnoBaza();
	}

	public boolean puedoEnvido() {
		if (bazas.size() == 1) {
			// es la primer baza, primera condicion para cantar envido
			Baza baza = bazas.get(0);

			if (tocaCartaMano()) { //no hay que responder un envite anterior
				if (baza.getCantidadCartasTiradas() >= 3) { // los que pueden cantar son el tercero o 4
					if (seCantoEnvido() == false)
						return true;

					return false;					
				}
			}
			return false; // hay que responder un envite
		}
		return false;
	}

	// SIEMPRE nos pedira los envites posibles el jugador que le toca jugar (jugadorActual)!
	public List<TipoEnvite> obtenerEnvitesPosibles() {
		List<TipoEnvite> respuestas = new ArrayList<TipoEnvite>();

		/*

		if (tocaCartaMano()) {
			// significa que no hay un envite pendiente a responder
			if (puedoEnvido()) {
				respuestas.add(TipoEnvite.Envido);
				respuestas.add(TipoEnvite.RealEnvido);
				respuestas.add(TipoEnvite.FaltaEnvido);
				respuestas.add(TipoEnvite.Truco);
				respuestas.add(TipoEnvite.IrAlMazo);

				return respuestas;
			} else {
				// no puede cantar envido pero va a verificar si puede cantar truco
				if (puedoTruco()) {
					respuestas.add(TipoEnvite.Truco);
					respuestas.add(TipoEnvite.IrAlMazo);
				}
				// falta obtener cual de los trucos se canto
			}
		} else {

			// toca responder un envite
			Baza baza = bazas.get(bazas.size()-1);//
			Movimiento mov = baza.obtenerUltimoMovimiento();
			TipoEnvite env = ((Envite) mov).getTipoEnvite();

			switch (env) {

		*/

		if (enviteActual == null) {
			// aun no se canto nada
			if (bazas.size() == 1) {
				if ((obtenerUltimaBaza().getCantidadCartasTiradas() == 2) ||
					(obtenerUltimaBaza().getCantidadCartasTiradas() == 3)) {

					respuestas.add(TipoEnvite.Envido);
					respuestas.add(TipoEnvite.RealEnvido);
					respuestas.add(TipoEnvite.FaltaEnvido);
					respuestas.add(TipoEnvite.Truco);
					respuestas.add(TipoEnvite.IrAlMazo);
				} else {
					// no le enviamos ninguna opcion. Lo obligamos a tirar solamente.
				}
			} else {
				// el juego esta en la segunda o tercer baza y no se canto nada aun!
				respuestas.add(TipoEnvite.Truco);
				respuestas.add(TipoEnvite.IrAlMazo);
			}
		} else {
			switch (enviteActual.getTipoEnvite()) {
				case Envido : {
					respuestas.add(TipoEnvite.EnvidoEnvido);
					respuestas.add(TipoEnvite.RealEnvido);
					respuestas.add(TipoEnvite.FaltaEnvido);
					respuestas.add(TipoEnvite.Quiero);
					respuestas.add(TipoEnvite.NoQuiero);
					respuestas.add(TipoEnvite.IrAlMazo);
					break;
				}
				case EnvidoEnvido : {
					respuestas.add(TipoEnvite.RealEnvido);
					respuestas.add(TipoEnvite.FaltaEnvido);
					respuestas.add(TipoEnvite.Quiero);
					respuestas.add(TipoEnvite.NoQuiero);
					respuestas.add(TipoEnvite.IrAlMazo);
					break;
				}
				case RealEnvido : {
					respuestas.add(TipoEnvite.FaltaEnvido);
					respuestas.add(TipoEnvite.Quiero);
					respuestas.add(TipoEnvite.NoQuiero);
					respuestas.add(TipoEnvite.IrAlMazo);
					break;
				}
				case FaltaEnvido : {
					respuestas.add(TipoEnvite.Quiero);
					respuestas.add(TipoEnvite.NoQuiero);
					respuestas.add(TipoEnvite.IrAlMazo);
					break;
				}
				case Truco : {
					if (puedoEnvido()) {
						respuestas.add(TipoEnvite.Envido);
						respuestas.add(TipoEnvite.RealEnvido);
						respuestas.add(TipoEnvite.FaltaEnvido);
					}
					respuestas.add(TipoEnvite.ReTruco);
					respuestas.add(TipoEnvite.Quiero);
					respuestas.add(TipoEnvite.NoQuiero);
					respuestas.add(TipoEnvite.IrAlMazo);
					break;
				}
				case ReTruco : {
					respuestas.add(TipoEnvite.ValeCuatro);
					respuestas.add(TipoEnvite.Quiero);
					respuestas.add(TipoEnvite.NoQuiero);
					respuestas.add(TipoEnvite.IrAlMazo);
					break;
				}
				case ValeCuatro : {
					respuestas.add(TipoEnvite.Quiero);
					respuestas.add(TipoEnvite.NoQuiero);
					respuestas.add(TipoEnvite.IrAlMazo);
					break;
				}
				case IrAlMazo :
					break;
				case NoQuiero :
					break;
				case Quiero :
					break;
				default :
					break;
			}
		}

		return respuestas;
	}

	public boolean puedoTruco() {
		if(tocaCartaMano() == true) //no toca responder un envite
		{
			if(seCantoTruco() == false)
				return true;
			
		}
		return false;
	}

	public boolean seCantoEnvido() {
		Baza baza = bazas.get(0);

		for(Movimiento mov: baza.getTurnosBaza()) {
			if(mov instanceof Envite) {
				Envite aux = (Envite) mov;

				if ((aux.getTipoEnvite() == TipoEnvite.Envido) ||
					(aux.getTipoEnvite() == TipoEnvite.EnvidoEnvido) ||
					(aux.getTipoEnvite() == TipoEnvite.RealEnvido) ||
					(aux.getTipoEnvite() == TipoEnvite.FaltaEnvido))

					return true;
			}
		}
		return false;
	}

	public boolean seCantoTruco(){
		for(Baza baza : bazas) {
			for(Movimiento mov: baza.getTurnosBaza())
			{
				if(mov instanceof Envite)
				{
					Envite aux = (Envite) mov;
				
					if(aux.getTipoEnvite() == TipoEnvite.Truco)
						return true;
				}
			}
		}
		return false;
	}

	public Jugador obtenerTurnoContestar() {		
		return obtenerUltimaBaza().obtenerTurnoContestar();
	}

	public Jugador getJugadorActual() {
		return jugadorActual;
	}

	public void setJugadorActual(Jugador jugadorActual) {
		this.jugadorActual = jugadorActual;
	}

	public byte getEnvidoPareja1() {
		return envidoPareja1;
	}

	public byte getEnvidoPareja2() {
		return envidoPareja2;
	}

	private Baza obtenerUltimaBaza() {
		return bazas.get(bazas.size() - 1);
	}

	public void agregarMovimiento(Jugador jugador, Movimiento movimiento) {
		Baza ultimaBaza = obtenerUltimaBaza();
		if(movimiento instanceof CartaTirada)
		{
			CartaJugador carta = obtenerCartaJugador((CartaTirada) movimiento);
			((CartaTirada) movimiento).setCartaJugador(carta);
			carta.setTirada(true);
		}
		ultimaBaza.agregarMovimiento(jugador, movimiento);
			
		
		if (movimiento instanceof Envite) {
			Envite envite = (Envite) movimiento;

			if (envite.getTipoEnvite().equals(TipoEnvite.Quiero)) {
				// primero, verifico si el Envite es un 'Quiero' de alguno de todos los Envidos
				if (enviteActual.sosAlgunEnvido()) {
					byte puntajeEnvido = 0;

					if (enviteActual.getTipoEnvite().equals(TipoEnvite.Envido))
						puntajeEnvido = 2;
					else if (enviteActual.getTipoEnvite().equals(TipoEnvite.EnvidoEnvido))
						puntajeEnvido = 4;
					else if (enviteActual.getTipoEnvite().equals(TipoEnvite.RealEnvido))
						puntajeEnvido = 3;
					else if (enviteActual.getTipoEnvite().equals(TipoEnvite.FaltaEnvido))
						puntajeEnvido = 30;

					// VER BIEN ESTO!!!
					// quizas sea mejor recorrer todos los movimientos y analizar cual es el puntaje
					// correspondiente al Envido ganado!


					// debemos analizar quien gana el Envido!
					Pareja ganadorEnvido = obtenerGanadorEnvido();
					// OJO, quizas el que gano el Envido ya alcanzo los 30 puntos y gana el Chico!
					actualizarPuntajePareja(ganadorEnvido, puntajeEnvido);
				} else
				// ahora, verifico si el Envite es un 'Quiero' de alguno de todos los Trucos
				if (enviteActual.sosAlgunTruco()) {

//					(enviteActual.getTipoEnvite().equals(TipoEnvite.Truco))		||
//					(enviteActual.getTipoEnvite().equals(TipoEnvite.ReTruco))	||
//					(enviteActual.getTipoEnvite().equals(TipoEnvite.ValeCuatro))

				}
			} else if (envite.getTipoEnvite().equals(TipoEnvite.NoQuiero)) {
				// 
			}
			enviteActual = (Envite) movimiento;
		} else if (movimiento instanceof CartaTirada) {
			
			if (ultimaBaza.getCantidadCartasTiradas() == 4) {
				// se arrojo la ultima carta de la ronda! hay que cerrar la baza
				jugadorActual = ultimaBaza.obtenerGanador();
			} else {
				jugadorActual = ordenJuego.get(ultimaBaza.getCantidadCartasTiradas());
			}
		}
	}

	private void actualizarPuntajePareja(Pareja pareja, int puntaje) {
		
	}
	
	public List<CartaJugadorDTO> obtenerCartasJugador (JugadorDTO jugador){
		
		List<CartaJugadorDTO> devolver = new ArrayList<CartaJugadorDTO>();
		for(CartaJugador cartaJugador: cartasJugador){
			
			if(cartaJugador.getJugador().sosJugador(jugador)){
				devolver.add(cartaJugador.toDTO());
			}
				
		}
		return devolver;
	}
	
	public CartaJugador obtenerCartaJugador (CartaTirada carta){
		
		for(CartaJugador aux : cartasJugador){
			
			if(aux.getCarta().getId()==(carta.getCartaJugador().getCarta().getId()))
				return aux;
		}
		return null;
	}

}

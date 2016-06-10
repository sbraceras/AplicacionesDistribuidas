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
	private byte envidoJugador1;
	@Transient
	private byte envidoJugador2;
	@Transient
	private byte envidoJugador3;
	@Transient
	private byte envidoJugador4;

	@Transient
	private byte puntajeTruco;


	public Mano() {
		
	}

	public Mano(Chico chico, int numeroMano, List<Jugador> ordenJuego, List<PuntajePareja> puntajes) {
		this.chico = chico;
		this.numeroMano = numeroMano;
		this.puntajes = puntajes;
		this.bazas = new ArrayList<Baza>();
		this.cartasJugador = new ArrayList<CartaJugador>();
		this.enviteActual = null;
		this.puntajeTruco = 1; // es el puntaje minimo que se va a ganar con el Truco
		this.jugadorActual = ordenJuego.get(0);
		this.mazo = new Mazo();

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

	public byte getPuntajeTruco() {
		return puntajeTruco;
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

	private byte obtenerPuntajeEnvido(boolean querido) {
		// recorremos todos los movimientos de la primer Baza (solo aqui se puede cantar Envido)
		// y analizamos cual es el puntaje acumulado del Envido!
		List<Movimiento> movimientos = bazas.get(0).getTurnosBaza();
		String cadenaDeEnvidos = "";

		for (Movimiento mov: movimientos) {
			if (mov instanceof Envite) {
				Envite envite = (Envite) mov;

				if (envite.sosAlgunEnvido())
					cadenaDeEnvidos.concat(envite.getTipoEnvite().name());
			}
		}

		if (cadenaDeEnvidos.isEmpty())
			return 0;
		else {
			if (querido)
				cadenaDeEnvidos.concat(TipoEnvite.Quiero.name());
			else
				cadenaDeEnvidos.concat(TipoEnvite.NoQuiero.name());

			return Envite.obtenerPuntajeEnvido(cadenaDeEnvidos);
		}
	}

	public Pareja obtenerGanadorEnvido() {
		// debemos obtener el Envido de TODOS los Jugadores ya que, segun las reglas,
		// el jugador que es 'mano' debe comenzar a decir su Envido, luego el Jugador
		// a su derecha, y asi, hasta el ultimo pie.
		envidoJugador1 = obtenerEnvidoJugador(ordenJuego.get(0));
		envidoJugador2 = obtenerEnvidoJugador(ordenJuego.get(1));
		envidoJugador3 = obtenerEnvidoJugador(ordenJuego.get(2));
		envidoJugador4 = obtenerEnvidoJugador(ordenJuego.get(3));

		// obtenemos el Envido mas alto de cada Pareja
		byte envidoPareja1 = envidoJugador1 > envidoJugador3 ? envidoJugador1 : envidoJugador3;
		byte envidoPareja2 = envidoJugador2 > envidoJugador4 ? envidoJugador2 : envidoJugador4;

		if (envidoPareja1 > envidoPareja2) {
			return obtenerParejaJugador(ordenJuego.get(0));
		} else if (envidoPareja1 < envidoPareja2) {
			return obtenerParejaJugador(ordenJuego.get(1));
		} else if (envidoPareja1 == envidoPareja2) {
			// obtengo la Pareja a la que pertenece el jugador que es 'mano' - ordenJuego.get(0) -
			return obtenerParejaJugador(ordenJuego.get(0));
		}
		return null;
	}

	private byte obtenerEnvidoJugador(Jugador jugador) {
		//********  HACER !!!  ********//
		return 0;
	}

	public Pareja obtenerParejaJugador(Jugador jugador) {
		if (puntajes.get(0).getPareja().tenesJugador(jugador)) {
			return puntajes.get(0).getPareja();
		}
		return puntajes.get(1).getPareja();
	}

	private Pareja obtenerParejaEnemiga(Jugador jugador) {
		if (puntajes.get(0).getPareja().tenesJugador(jugador)) {
			return puntajes.get(1).getPareja();
		}
		return puntajes.get(0).getPareja();
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
				
				if ((obtenerUltimaBaza().getCantidadCartasTiradas() == 2) ||
					(obtenerUltimaBaza().getCantidadCartasTiradas() == 3)) {
					respuestas.add(TipoEnvite.IrAlMazo);
				}
			}
		} else {
			switch (enviteActual.getTipoEnvite()) {
				case Envido : {
					respuestas.add(TipoEnvite.EnvidoEnvido);
					respuestas.add(TipoEnvite.RealEnvido);
					respuestas.add(TipoEnvite.FaltaEnvido);
					respuestas.add(TipoEnvite.Quiero);
					respuestas.add(TipoEnvite.NoQuiero);
					break;
				}
				case EnvidoEnvido : {
					respuestas.add(TipoEnvite.RealEnvido);
					respuestas.add(TipoEnvite.FaltaEnvido);
					respuestas.add(TipoEnvite.Quiero);
					respuestas.add(TipoEnvite.NoQuiero);
					break;
				}
				case RealEnvido : {
					respuestas.add(TipoEnvite.FaltaEnvido);
					respuestas.add(TipoEnvite.Quiero);
					respuestas.add(TipoEnvite.NoQuiero);
					break;
				}
				case FaltaEnvido : {
					respuestas.add(TipoEnvite.Quiero);
					respuestas.add(TipoEnvite.NoQuiero);
					break;
				}
				case Truco : {
					if (puedoEnvido()) {
						// el Envido esta primero!
						respuestas.add(TipoEnvite.Envido);
						respuestas.add(TipoEnvite.RealEnvido);
						respuestas.add(TipoEnvite.FaltaEnvido);
					}
					respuestas.add(TipoEnvite.ReTruco);
					respuestas.add(TipoEnvite.Quiero);
					respuestas.add(TipoEnvite.NoQuiero);
					break;
				}
				case ReTruco : {
					respuestas.add(TipoEnvite.ValeCuatro);
					respuestas.add(TipoEnvite.Quiero);
					respuestas.add(TipoEnvite.NoQuiero);
					break;
				}
				case ValeCuatro : {
					respuestas.add(TipoEnvite.Quiero);
					respuestas.add(TipoEnvite.NoQuiero);
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

			if ((obtenerUltimaBaza().getCantidadCartasTiradas() == 2) ||
				(obtenerUltimaBaza().getCantidadCartasTiradas() == 3)) {
				respuestas.add(TipoEnvite.IrAlMazo);
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

	public byte getEnvidoJugador1() {
		return envidoJugador1;
	}

	public byte getEnvidoJugador2() {
		return envidoJugador2;
	}

	public byte getEnvidoJugador3() {
		return envidoJugador3;
	}

	public byte getEnvidoJugador4() {
		return envidoJugador4;
	}

	private Baza obtenerUltimaBaza() {
		return bazas.get(bazas.size() - 1);
	}

	public void agregarMovimiento(Jugador jugador, Movimiento movimiento) {
		Baza ultimaBaza = obtenerUltimaBaza();

		if (movimiento instanceof CartaTirada) {
			CartaTirada cartaTirada = (CartaTirada) movimiento;

			// obtengo la CartaJugador en estado persistente!
			CartaJugador cartaJugador = obtenerCartaJugador(cartaTirada);
			cartaJugador.setTirada(true);
			cartaTirada.setNumeroTurno(ultimaBaza.getTurnosBaza().size() + 1);
			cartaTirada.setCartaJugador(cartaJugador);

			ultimaBaza.agregarMovimiento(jugador, cartaTirada);

			if (ultimaBaza.getCantidadCartasTiradas() == 4) {
				// se arrojo la ultima carta de la ronda! hay que cerrar la baza
				jugadorActual = ultimaBaza.cerrarBaza();
				
				if(jugadorActual == null)
				{
					//se produjo un empate en la baza
					if(ultimaBaza.getNumeroBaza() == 1)
					{
						bazas.add(new Baza(this, 2, this.ordenJuego));
						//juega el jugador mano
						jugadorActual = ordenJuego.get(0);
					}
					else
						if(ultimaBaza.getNumeroBaza() == 2) {
							//se produjo un empate en la segunda baza
							if(bazas.get(0).getGanador()== null){ //tambien se empato la primera
								bazas.add(new Baza(this, 3, this.ordenJuego));
								//juega el jugador mano
								jugadorActual = ordenJuego.get(0);
							}
							else {
								//hubo un ganador en la primera
								//se tiene que terminar la mano
								chico.actualizarPuntajePareja(puntajeTruco, obtenerParejaJugador(bazas.get(0).getGanador()));
							}
						}
				}
				
				
				
				//terminar
				
				
				
			} else {
				jugadorActual = ordenJuego.get(ultimaBaza.getCantidadCartasTiradas());
			}
		} else if (movimiento instanceof Envite) {
			Envite envite = (Envite) movimiento;

			ultimaBaza.agregarMovimiento(jugador, envite);

			if ((envite.getTipoEnvite().equals(TipoEnvite.Quiero))	||
				(envite.getTipoEnvite().equals(TipoEnvite.ReTruco))	||
				(envite.getTipoEnvite().equals(TipoEnvite.ValeCuatro))) {

				// primero, verifico si el Envite es un 'Quiero' de alguno de todos los Envidos
				if (enviteActual.sosAlgunEnvido()) {
					// debemos calcular el puntaje que corresponde a la cadena de Envidos...
					byte puntajeEnvido = obtenerPuntajeEnvido(true);
					// debemos analizar quien gana el Envido!
					Pareja ganadorEnvido = obtenerGanadorEnvido();
					// Salvamos el caso de la falta envido
					if(puntajeEnvido == 100){
						
						//HACERRRRRRRRR*///
						puntajeEnvido = calcularPuntajeFaltaEnvido(ganadorEnvido);
					}
					// OJO, quizas el que gano el Envido ya alcanzo los 30 puntos y gana el Chico!
					chico.actualizarPuntajePareja(puntajeEnvido, ganadorEnvido);
					
					/* PREGUNTO SI NO TERMINO EL CHICO */
					if(!chico.isTerminado()){
						jugadorActual = obtenerUltimaBaza().getOrdenJuego().get(obtenerUltimaBaza().getCantidadCartasTiradas()-1);					
					}
					
					
				} else
				// ahora, verifico si el Envite es un 'Quiero', o Retruco o Valecuatro de alguno de todos los Trucos
				if (enviteActual.sosAlgunTruco()) {
					// quisieron algun Truco...
					puntajeTruco++;
					
					/* esto lo agregamos nosotros */
					jugadorActual = (ordenJuego.indexOf(envite.getJugador()) == 3) ? ordenJuego.get(2) : ordenJuego.get(3);
					
				}
			} else if (envite.getTipoEnvite().equals(TipoEnvite.NoQuiero)) {
				// primero, verifico si el Envite es un 'NoQuiero' de alguno de todos los Envidos
				if (enviteActual.sosAlgunEnvido()) {
					// debemos calcular el puntaje que corresponde a la cadena de Envidos...
					byte puntajeEnvido = obtenerPuntajeEnvido(false);
					// los puntos del Envido se los lleva la Pareja contraria del Jugador que dijo NoQuiero 
					Pareja ganadorEnvido = obtenerParejaEnemiga(envite.getJugador());
					// OJO, quizas el que gano el Envido ya alcanzo los 30 puntos y gana el Chico!

					// CERRAR BAZA? CERRAR MANO?
					chico.actualizarPuntajePareja(puntajeEnvido, ganadorEnvido);
					
					
				} else
				// ahora, verifico si el Envite es un 'NoQuiero' de alguno de todos los Trucos
				if (enviteActual.sosAlgunTruco()) {
					// NO quisieron algun Truco, por lo tanto cerramos la Mano
					
					// los puntos del Truco se los lleva la Pareja contraria del Jugador que dijo NoQuiero 
					Pareja ganadorTruco = obtenerParejaEnemiga(envite.getJugador());
					// OJO, quizas el que gano el Truco ya alcanzo los 30 puntos y gana el Chico!

					// CERRAR BAZA Y LUEGO LA MANO!
					chico.actualizarPuntajePareja(puntajeTruco, ganadorTruco);
					
					
				}
			} else if (envite.getTipoEnvite().equals(TipoEnvite.IrAlMazo)) {
				// ANALIZAR...
			} else {
				// canto algo! pasamos el turno al siguiente
				if (ultimaBaza.getNumeroBaza() == 1) {					
					jugadorActual = (ordenJuego.indexOf(envite.getJugador()) == 3) ? ordenJuego.get(2) : ordenJuego.get(3);
				} else {
					// Como es la segunda o tercer baza, ya fue analizado retruco y vale cuatro que son las dos unicas opciones
				}
			}
			enviteActual = envite;
		}
	}

	public List<CartaJugadorDTO> obtenerCartasJugador(JugadorDTO jugador) {
		List<CartaJugadorDTO> devolver = new ArrayList<CartaJugadorDTO>();

		for(CartaJugador cartaJugador: cartasJugador) {
			if(cartaJugador.getJugador().sosJugador(jugador)) {
				devolver.add(cartaJugador.toDTO());
			}
		}
		return devolver;
	}

	public CartaJugador obtenerCartaJugador(CartaTirada carta) {
		for(CartaJugador aux : cartasJugador) {
			if (aux.getCarta().getId() == (carta.getCartaJugador().getCarta().getId()))
				return aux;
		}
		return null;
	}

}

package bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import dtos.BazaDTO;
import dtos.CartaJugadorDTO;
import dtos.EnviteDTO;
import dtos.ManoDTO;
import enums.TipoEnvite;


/**
 * La mano esta compuesta por varias bazas
 * 
 * Identifica quien es el que comienza la mano
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
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn (name = "id_mano")
	private List<Baza> bazas;
	@OneToMany (cascade = CascadeType.ALL) /* fetch = FetchType.EAGER)*/
	@JoinColumn (name = "id_mano")
	private List<CartaJugador> cartasJugador;
	@Transient
	private Envite enviteActual;
	@Transient
	private Mazo mazo;
	@Transient
	private List<Jugador> ordenJuego;


	public Mano() {
	}

	public Mano(int numeroMano, List<Jugador> ordenJuego) {
		this.numeroMano = numeroMano;
		this.bazas = new ArrayList<Baza>();
		this.enviteActual = null;
		this.mazo = new Mazo();
		this.cartasJugador = new ArrayList<CartaJugador>();
		this.ordenJuego = ordenJuego;

		repartirCartas(ordenJuego);

		this.bazas.add(new Baza(1));
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

	
	/* DESARROLLAR */
	
	
	public Jugador obtenerGanadorEnvido() {
		return null;
	}
	////////////////////////////////////
	/* NO HIZO FALTA LO HICE DE AFUERA */
	/////////////////////////////////
	public void recalcularOrdenJuego() {
	
	}
	
	/////////////////*****
	
	/*    ver bien esto      */
	
	
	////***************///
	public void nuevaBaza(int numeroBaza, ArrayList<Jugador> ordenJuego) {
	
		
	}
	
	public ManoDTO toDTO() {
		
		ManoDTO dto = new ManoDTO();
		dto.setId(this.id);
		dto.setNumeroMano(this.numeroMano);
		if(enviteActual!=null){
			dto.setEnviteActual((EnviteDTO) this.enviteActual.toDTO());
		}
		ArrayList<BazaDTO> bazasDto = new ArrayList<BazaDTO>();
		
		for(int i=0; i<bazas.size();i++){
			bazasDto.add(bazas.get(i).toDTO());
			
		}
		dto.setBazas(bazasDto);
		ArrayList<CartaJugadorDTO> cartasDto = new ArrayList<CartaJugadorDTO>();
		
		for(int i=0; i<cartasJugador.size();i++)
		{
			cartasDto.add(cartasJugador.get(i).toDTO());
		}
		dto.setCartasJugador(cartasDto);
		return dto;
	}

	public boolean tocaCartaMano() {
		Baza baza = bazas.get(bazas.size() - 1); //obtengo la ultima baza

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
		return bazas.get(bazas.size()).obtenerTurnoBaza();
	}
	
	public void cerrarBazaMano(List<Pareja> parejas){	
		Baza baza = bazas.get(bazas.size());

		if (baza.obtenerCantidadCartasTiradas() == 4) { //hay que cerrar la baza
			baza.definirGanador(parejas);
		}
	}
	
	public boolean puedoEnvido(){
		
		if(bazas.size() == 1) //es la primer baza, se puede cantar envido
		{
			Baza baza = bazas.get(bazas.size());
			
			if(tocaCartaMano()) //no hay que responder un envite anterior
			{
				if(baza.obtenerCantidadCartasTiradas() >= 3) //los que pueden cantar son el tercero o 4
				{
					if(seCantoEnvido() == false)
					{
						return true;
					}
					return false;
					
				}
			}
			return false; //hay que responder un envite
		}
		
		return false;
		
	}
	
	
	public List<TipoEnvite> opcionesCanto(){
		
		List<TipoEnvite> respuestas = new ArrayList<TipoEnvite>();
		
		if(puedoEnvido() == true){
			
			respuestas.add(TipoEnvite.Envido);
			respuestas.add(TipoEnvite.FaltaEnvido);
			respuestas.add(TipoEnvite.RealEnvido);
			respuestas.add(TipoEnvite.Truco);
			respuestas.add(TipoEnvite.IrAlMazo);
			
			return respuestas;
		}
		else{ //no puede cantar envido pero va a verificar si puede cantar truco
			
			if(puedoTruco() == true)
			{
				respuestas.add(TipoEnvite.Truco);
				respuestas.add(TipoEnvite.IrAlMazo);
			}
			
			
		}
				
		return null;
		
	}
	
	public boolean puedoTruco(){
		
		if(tocaCartaMano() == true) //no toca responder un envite
		{
			if(seCantoTruco() == false)
				return true;
			
		}
		return false;
	}
	
	
	public boolean seCantoEnvido(){
		
		Baza baza = bazas.get(0);
		
		for(Movimiento mov: baza.getTurnosBaza())
		{
			if(mov instanceof Envite)
			{
				Envite aux = (Envite) mov;
				
				if(aux.getTipoEnvite() == TipoEnvite.Envido || aux.getTipoEnvite() == TipoEnvite.FaltaEnvido || aux.getTipoEnvite() == TipoEnvite.RealEnvido)
					return true;
			}
		}
		return false;
		
	}
	
	public boolean seCantoTruco(){
		
		
		for(Baza baza : bazas)
		{
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
	
	
	
}

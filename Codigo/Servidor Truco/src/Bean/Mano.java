package Bean;

import java.lang.reflect.Array;
import java.util.ArrayList;

import DTO.ManoDTO;

/**
 * La mano esta compuesta por varias bazas
 * 
 * Identifica quien es el que comienza la mano
**/
public class Mano {
	private int id;
	private int numeroMano;
	private ArrayList<Baza> bazas;
	private ArrayList<CartaJugador> cartasJugador;
	private Envite enviteActual;
	private Mazo mazo;
	
	
	
	public Mano() {
	}

	public Mano(int id, int numeroMano, ArrayList<Baza> bazas, ArrayList<CartaJugador> cartasJugador,
			Envite enviteActual, Mazo mazo) {
		this.id = id;
		this.numeroMano = numeroMano;
		this.bazas = bazas;
		this.cartasJugador = cartasJugador;
		this.enviteActual = enviteActual;
		this.mazo = mazo;
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

	public ArrayList<Baza> getBazas() {
		return bazas;
	}

	public void setBazas(ArrayList<Baza> bazas) {
		this.bazas = bazas;
	}

	public ArrayList<CartaJugador> getCartasJugador() {
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

	public Jugador obtenerGanadorEnvido() {
		return null;
	}
	
	public void recalcularOrdenJuego() {
	
	}
	
	public void repartirCartas() {
	
	}
	
	public void nuevaBaza(int numeroBaza, ArrayList<Jugador> ordenJuego) {
	
	}
	
	public ManoDTO toDTO() {
		
		return null;
	}
}

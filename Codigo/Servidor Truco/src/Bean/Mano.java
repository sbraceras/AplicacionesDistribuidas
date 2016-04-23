package Bean;

import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.persistence.*;

import DTO.ManoDTO;

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
	private int id;
	@Column (name = "id_mano")
	private int numeroMano;
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_mano")
	private ArrayList<Baza> bazas;
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_mano")
	private ArrayList<CartaJugador> cartasJugador;
	/*No se persiste el envite Actual */
	private Envite enviteActual;
	/*No se persiste el mazo */
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

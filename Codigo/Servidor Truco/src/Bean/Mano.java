package Bean;

import java.util.ArrayList;
import java.util.List;

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
	@Column (name = "nro_mano")
	private int numeroMano;
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_mano")
	private List<Baza> bazas;
	@OneToMany (cascade = CascadeType.ALL)
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
		int numeroJugador=0;
		while(cartasJugador.size()<12){
			
			CartaJugador carta= new CartaJugador(ordenJuego.get(numeroJugador), mazo.obtenerCarta(), false);
			if(numeroJugador ==3)
			{
				numeroJugador=0;
			}
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

	public Jugador obtenerGanadorEnvido() {
		return null;
	}
	
	public void recalcularOrdenJuego() {
	
	}
	
	
	public void nuevaBaza(int numeroBaza, ArrayList<Jugador> ordenJuego) {
	
	}
	
	public ManoDTO toDTO() {
		
		return null;
	}
}

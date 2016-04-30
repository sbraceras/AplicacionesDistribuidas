package Bean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import DTO.BazaDTO;
import DTO.CartaDTO;
import DTO.CartaJugadorDTO;
import DTO.EnviteDTO;
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
		int numeroJugador=0;
		while(cartasJugador.size()<12){
			if(numeroJugador>3)
				numeroJugador =0;
			CartaJugador carta= new CartaJugador(ordenJuego.get(numeroJugador), mazo.obtenerCarta(), false);
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
}

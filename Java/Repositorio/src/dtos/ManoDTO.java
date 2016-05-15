package dtos;

import java.util.List;


public class ManoDTO {

	
	private int id;
	private int numeroMano;
	private List<BazaDTO> bazas;
	private List<CartaJugadorDTO> cartasJugador;
	private EnviteDTO enviteActual;
	// private Mazo mazo; CREO QUE NO HACE FALTA EL MAZO
	
	
	public ManoDTO() {
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
	public List<BazaDTO> getBazas() {
		return bazas;
	}
	public void setBazas(List<BazaDTO> bazas) {
		this.bazas = bazas;
	}
	public List<CartaJugadorDTO> getCartasJugador() {
		return cartasJugador;
	}
	public void setCartasJugador(List<CartaJugadorDTO> cartasJugador) {
		this.cartasJugador = cartasJugador;
	}
	public EnviteDTO getEnviteActual() {
		return enviteActual;
	}
	public void setEnviteActual(EnviteDTO enviteActual) {
		this.enviteActual = enviteActual;
	}
	
	
}

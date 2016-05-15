package dtos;


public class CartaJugadorDTO {
	
	
	private int id;
	private JugadorDTO jugador;
	private CartaDTO carta;
	private boolean tirada;
	
	public CartaJugadorDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public JugadorDTO getJugador() {
		return jugador;
	}

	public void setJugador(JugadorDTO jugador) {
		this.jugador = jugador;
	}

	public CartaDTO getCarta() {
		return carta;
	}

	public void setCarta(CartaDTO carta) {
		this.carta = carta;
	}

	public boolean isTirada() {
		return tirada;
	}

	public void setTirada(boolean tirada) {
		this.tirada = tirada;
	}

	
	
}

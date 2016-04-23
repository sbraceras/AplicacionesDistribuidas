package Bean;


public class CartaJugador {
	private int id;
	private Jugador jugador;
	private Carta carta;
	private boolean tirada;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Jugador getJugador() {
		return jugador;
	}
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	public Carta getCarta() {
		return carta;
	}
	public void setCarta(Carta carta) {
		this.carta = carta;
	}
	public boolean isTirada() {
		return tirada;
	}
	public void setTirada(boolean tirada) {
		this.tirada = tirada;
	}
	public CartaJugador(int id, Jugador jugador, Carta carta, boolean tirada) {
		this.id = id;
		this.jugador = jugador;
		this.carta = carta;
		this.tirada = tirada;
	}
	public CartaJugador() {
	}
	
	
}

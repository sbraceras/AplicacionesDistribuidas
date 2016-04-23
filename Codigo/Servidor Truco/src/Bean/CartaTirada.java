package Bean;

////falta hacer persistencia


public class CartaTirada extends Movimiento {
	private Object cartaJugador;

	public CartaTirada(Object cartaJugador) {
		this.cartaJugador = cartaJugador;
	}

	public CartaTirada() {
	}

	public Object getCartaJugador() {
		return cartaJugador;
	}

	public void setCartaJugador(Object cartaJugador) {
		this.cartaJugador = cartaJugador;
	}
	
	
}

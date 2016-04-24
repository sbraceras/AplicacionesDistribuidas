package Bean;

import javax.persistence.*;

@DiscriminatorValue("ct")
public class CartaTirada extends Movimiento {
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_cartaJugador")
	private CartaJugador cartaJugador;

	public CartaTirada(CartaJugador cartaJugador) {
		this.cartaJugador = cartaJugador;
	}

	public CartaTirada() {
	}

	public Object getCartaJugador() {
		return cartaJugador;
	}

	public void setCartaJugador(CartaJugador cartaJugador) {
		this.cartaJugador = cartaJugador;
	}
	
	
}

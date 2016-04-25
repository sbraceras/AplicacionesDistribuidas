package DTO;



public class CartaTiradaDTO extends MovimientoDTO {

	
	private CartaJugadorDTO cartaJugador;

	public CartaTiradaDTO() {
		
		super();
	}

	public CartaJugadorDTO getCartaJugador() {
		return cartaJugador;
	}

	public void setCartaJugador(CartaJugadorDTO cartaJugador) {
		this.cartaJugador = cartaJugador;
	}
	
	
}

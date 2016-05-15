package bean;

import javax.persistence.*;

import dtos.CartaTiradaDTO;


@Entity
@DiscriminatorValue("ct")
public class CartaTirada extends Movimiento {
	@OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn (name = "id_cartaJugador")
	private CartaJugador cartaJugador;

	public CartaTirada(CartaJugador cartaJugador) {
		super();
		this.cartaJugador = cartaJugador;
	}

	public CartaTirada() {
	}
	
	public CartaTiradaDTO toDTO(){
		
		
		CartaTiradaDTO dto = new CartaTiradaDTO();
		dto.setCartaJugador(this.cartaJugador.toDTO());
		dto.setFechaHora(super.fechaHora);
		dto.setId(super.id);
		dto.setNumeroTurno(super.numeroTurno);
		return dto;
	}

	public Object getCartaJugador() {
		return cartaJugador;
	}

	public void setCartaJugador(CartaJugador cartaJugador) {
		this.cartaJugador = cartaJugador;
	}
	
	
}

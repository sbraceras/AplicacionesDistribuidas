package Bean;

import javax.persistence.*;

import DTO.CartaJugadorDTO;





@Entity
@Table (name = "CartasJugador")
public class CartaJugador {
	
	@Id
	@Column (name = "id_cartaJugador", nullable = false)
	private int id;
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_jugador")
	private Jugador jugador;
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_carta")
	private Carta carta;
	@Column (columnDefinition = "bit")
	private boolean tirada;
	
	public CartaJugador(Jugador jugador, Carta carta, boolean tirada) {
		this.jugador = jugador;
		this.carta = carta;
		this.tirada = tirada;
	}
	public CartaJugador() {
	}
	
	public CartaJugadorDTO toDTO(){
		
		CartaJugadorDTO dto = new CartaJugadorDTO();
		
		dto.setCarta(this.carta.toDTO());
		dto.setId(this.id);
		dto.setJugador(this.jugador.toDTO());
		dto.setTirada(this.tirada);
		
		return dto;
	}
	
	
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

	
	
}

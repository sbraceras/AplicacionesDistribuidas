package bean;

import javax.persistence.*;

import dtos.CartaDTO;

import enums.Palo;


/**
 * El atributo posicionValor, que sirve para el caso de tirar una carta ya sea jugando normal o en truco. Ya que por ejemplo el ancho de espada va a tener posicion 1, y en el caso que haya valores iguales como por ejemplo el numero "3", se le asigna la misma posicion a la carta.
**/

@Entity
@Table (name = "Cartas")
public class Carta {
	@Id
	@Column (name = "id_carta", nullable = false)
	private int id;
	@Column (columnDefinition = "tinyint")
	private Palo palo;
	@Column
	private int numero;
	@Column (name = "posicion_valor")
	private int posicionValor;
	
	public Carta(Palo palo, int numero, int posicionValor) {
		
		this.palo = palo;
		this.numero = numero;
		this.posicionValor = posicionValor;
	}
	
	
		
	public Carta() {
	}
	
	public CartaDTO toDTO(){
		
		CartaDTO dto = new CartaDTO();
		dto.setId(this.id);
		dto.setNumero(this.numero);
		dto.setPalo(this.palo);
		dto.setPosicionValor(this.posicionValor);
		return dto;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Palo getPalo() {
		return palo;
	}
	public void setPalo(Palo palo) {
		this.palo = palo;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getPosicionValor() {
		return posicionValor;
	}
	public void setPosicionValor(int posicionValor) {
		this.posicionValor = posicionValor;
	}
	
	
	
	
	
}

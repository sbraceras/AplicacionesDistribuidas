package dtos;

import java.io.Serializable;

import enums.Palo;

public class CartaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private Palo palo;
	private int numero;
	private int posicionValor;

	public CartaDTO() {
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

	@Override
	public String toString() {
		return numero + " de " + palo.name();
	}

}

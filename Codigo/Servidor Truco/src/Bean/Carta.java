package Bean;

/**
 * El atributo posicionValor, que sirve para el caso de tirar una carta ya sea jugando normal o en truco. Ya que por ejemplo el ancho de espada va a tener posicion 1, y en el caso que haya valores iguales como por ejemplo el numero "3", se le asigna la misma posicion a la carta.
**/


public class Carta {
	private int id;
	private Palo palo; //////////VER COMO HACER ENUMERATION
	private int numero;
	private int posicionValor;
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
	public Carta(int id, Palo palo, int numero, int posicionValor) {
		this.id = id;
		this.palo = palo;
		this.numero = numero;
		this.posicionValor = posicionValor;
	}
	public Carta() {
	}
	
	
}

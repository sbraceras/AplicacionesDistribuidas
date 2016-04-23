package Bean;

import java.util.ArrayList;

public class Mazo {
	private int id;
	private ArrayList<Carta> cartas;
	public void iniciarMazo() {
	
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(ArrayList<Carta> cartas) {
		this.cartas = cartas;
	}

	public Mazo() {
	}

	public Mazo(int id, ArrayList<Carta> cartas) {
		this.id = id;
		this.cartas = cartas;
	}

	public Carta obtenerCarta() {
		return null;
	}
}

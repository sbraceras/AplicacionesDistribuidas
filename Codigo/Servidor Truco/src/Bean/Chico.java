package Bean;

import java.util.ArrayList;

import DTO.ChicoDTO;

/**
 * Chico es la partida corta de 15 puntos
**/
public class Chico {
	private int id;
	private ArrayList<Mano> manos;
	private ArrayList<PuntajePareja> puntajes;
	private int puntajeMaximo;
	
	
	public Chico() {
	}

	public Chico(int id, ArrayList<Mano> manos, ArrayList<PuntajePareja> puntajes, int puntajeMaximo) {
		this.id = id;
		this.manos = manos;
		this.puntajes = puntajes;
		this.puntajeMaximo = puntajeMaximo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Mano> getManos() {
		return manos;
	}

	public void setManos(ArrayList<Mano> manos) {
		this.manos = manos;
	}

	public ArrayList<PuntajePareja> getPuntajes() {
		return puntajes;
	}

	public void setPuntajes(ArrayList<PuntajePareja> puntajes) {
		this.puntajes = puntajes;
	}

	public int getPuntajeMaximo() {
		return puntajeMaximo;
	}

	public void setPuntajeMaximo(int puntajeMaximo) {
		this.puntajeMaximo = puntajeMaximo;
	}

	public Mano obtenerUltimaMano() {
		return null;///////
	}
	
	public void actualizarPuntajePareja(int puntaje, Pareja pareja) {
	
	}
	
	public void nuevaMano(ArrayList<Jugador> ordenJuego, int numeroMano) {
	
	}
	
	public ChicoDTO toDTO() {
		return null;//////
	}
}

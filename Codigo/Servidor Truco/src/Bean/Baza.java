package Bean;

import java.util.ArrayList;

import DTO.BazaDTO;

/**
 * La baza es cuando uno arroja 1 carta de las 3 que posee
**/
public class Baza {
	private int id;
	private ArrayList<Movimiento> turnosBaza;
	private int numeroBaza;
	private Jugador ganador;
	private ArrayList<Jugador> ordenJuego;
	public Jugador obtenerGanador() {
		return null;///////////
	}
	
	public BazaDTO toDTO() {
		return null;////////////
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Movimiento> getTurnosBaza() {
		return turnosBaza;
	}

	public void setTurnosBaza(ArrayList<Movimiento> turnosBaza) {
		this.turnosBaza = turnosBaza;
	}

	public int getNumeroBaza() {
		return numeroBaza;
	}

	public void setNumeroBaza(int numeroBaza) {
		this.numeroBaza = numeroBaza;
	}

	public Jugador getGanador() {
		return ganador;
	}

	public void setGanador(Jugador ganador) {
		this.ganador = ganador;
	}

	public ArrayList<Jugador> getOrdenJuego() {
		return ordenJuego;
	}

	public void setOrdenJuego(ArrayList<Jugador> ordenJuego) {
		this.ordenJuego = ordenJuego;
	}

	public Baza(int id, ArrayList<Movimiento> turnosBaza, int numeroBaza, Jugador ganador,
			ArrayList<Jugador> ordenJuego) {
		this.id = id;
		this.turnosBaza = turnosBaza;
		this.numeroBaza = numeroBaza;
		this.ganador = ganador;
		this.ordenJuego = ordenJuego;
	}

	public Baza() {
	}
	
	
}

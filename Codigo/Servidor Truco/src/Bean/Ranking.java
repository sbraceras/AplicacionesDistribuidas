package Bean;

import java.util.ArrayList;

import DTO.RankingDTO;

public class Ranking {
	private int id;
	private ArrayList<Partido> partidos;
	private int puntos;
	private int cantidadGanadas;
	
	
	public Ranking() {
	}

	public Ranking(int id, ArrayList<Partido> partidos, int puntos, int cantidadGanadas) {
		this.id = id;
		this.partidos = partidos;
		this.puntos = puntos;
		this.cantidadGanadas = cantidadGanadas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Partido> getPartidos() {
		return partidos;
	}

	public void setPartidos(ArrayList<Partido> partidos) {
		this.partidos = partidos;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getCantidadGanadas() {
		return cantidadGanadas;
	}

	public void setCantidadGanadas(int cantidadGanadas) {
		this.cantidadGanadas = cantidadGanadas;
	}

	public float getPromedio() {
		return 0;
	}
	
	public RankingDTO toDTO() {
		return null;
	}
}

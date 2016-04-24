package Bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import DTO.RankingDTO;


@Entity
@Table (name = "Rankings")
public class Ranking {
	@Id
	@Column (name = "id_ranking")
	private int id;
	@ManyToMany (cascade = CascadeType.ALL)
	@JoinTable (name = "Ranking_Partido", 
	joinColumns ={@JoinColumn (name = "id_partido")},
	inverseJoinColumns = {@JoinColumn (name = "id_ranking")})
	private List<Partido> partidos;
	@Column
	private int puntos;
	@Column (name = "cant_ganadas")
	private int cantidadGanadas;
	
	
	public Ranking() {
	}

	public Ranking(int id, int puntos, int cantidadGanadas) {
		this.id = id;
		this.partidos = new ArrayList<Partido>();
		this.puntos = puntos;
		this.cantidadGanadas = cantidadGanadas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Partido> getPartidos() {
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

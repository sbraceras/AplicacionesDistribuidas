package Bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import DTO.PartidoDTO;
import DTO.RankingDTO;


@Entity
@Table (name = "Rankings")
public class Ranking {
	@Id
	@Column (name = "id_ranking")
	@GeneratedValue
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
		
		this.partidos = new ArrayList<Partido>();
		this.puntos = 0;
		this.cantidadGanadas = 0;
	}
	
	public RankingDTO toDTO() {

		RankingDTO dto = new RankingDTO();
		dto.setCantidadGanadas(this.cantidadGanadas);
		dto.setId(this.id);
		dto.setPuntos(this.puntos);
		ArrayList<PartidoDTO>partidosDto = new ArrayList<PartidoDTO>();
		for(int i=0; i<partidos.size();i++)
		{
			partidosDto.add(partidos.get(i).toDTO());
		}
		dto.setPartidos(partidosDto);
		return dto;
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
		
		return puntos/partidos.size();
	}
	
	
}

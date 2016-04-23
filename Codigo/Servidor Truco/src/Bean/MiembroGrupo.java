package Bean;

import javax.persistence.*;
import DTO.JugadorDTO;
import DTO.RankingDTO;

/**
 * Intermediario entre el grupo y el miembro
**/

@Entity
@Table (name = "Miembros_Grupo")
public class MiembroGrupo {
	@Id
	@Column (name = "id_miembro", nullable = false)
	private int id;
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_jugador")
	private Jugador jugador;
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_ranking")
	private Ranking ranking;
	@Column (columnDefinition = "bit")
	private boolean activo;
	
	
	
	
	public MiembroGrupo() {
	}

		
	public MiembroGrupo(int id, Jugador jugador, Ranking ranking, boolean activo) {
		this.id = id;
		this.jugador = jugador;
		this.ranking = ranking;
		this.activo = activo;
	}
	
	public boolean tenesMiembro(JugadorDTO jugador) {
		return false;
	}

	public RankingDTO obtenerRanking() {
		return null;
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

	public Ranking getRanking() {
		return ranking;
	}

	public void setRanking(Ranking ranking) {
		this.ranking = ranking;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}

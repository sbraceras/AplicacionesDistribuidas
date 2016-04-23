package Bean;

import DTO.JugadorDTO;
import DTO.RankingDTO;

/**
 * Intermediario entre el grupo y el miembro
**/
public class MiembroGrupo {
	private int id;
	private int idGrupo;
	private Jugador jugador;
	private Ranking ranking;
	private boolean activo;
	public boolean tenesMiembro(JugadorDTO jugador) {
		return false;
	}
	
	public MiembroGrupo() {
	}

	public MiembroGrupo(int id, int idGrupo, Jugador jugador, Ranking ranking, boolean activo) {
		this.id = id;
		this.idGrupo = idGrupo;
		this.jugador = jugador;
		this.ranking = ranking;
		this.activo = activo;
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

	public int getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
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

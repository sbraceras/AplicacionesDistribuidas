package DTO;


public class MiembroGrupoDTO {
	
	private int id;
	private JugadorDTO jugador;
	private RankingDTO ranking;
	private boolean activo;
	
	public MiembroGrupoDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public JugadorDTO getJugador() {
		return jugador;
	}

	public void setJugador(JugadorDTO jugador) {
		this.jugador = jugador;
	}

	public RankingDTO getRanking() {
		return ranking;
	}

	public void setRanking(RankingDTO ranking) {
		this.ranking = ranking;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	
	
}

package Bean;

import javax.persistence.*;
import DTO.JugadorDTO;
import DTO.MiembroGrupoDTO;
import DTO.RankingDTO;
import ENUMS.TipoMiembro;

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
	@Column (columnDefinition = "int")
	private TipoMiembro tipoMiembro;
	
	
	
	
	public MiembroGrupo() {
	}

		
	public MiembroGrupo(Jugador jugador) {
		
		this.jugador = jugador;
		this.ranking = new Ranking();
		this.activo = true;
	}
	
	public MiembroGrupoDTO toDTO(){
		
		MiembroGrupoDTO dto = new MiembroGrupoDTO();
		
		dto.setActivo(this.activo);
		dto.setId(this.id);
		dto.setJugador(this.jugador.toDTO());
		dto.setRanking(this.ranking.toDTO());
		dto.setTipoMiembro(tipoMiembro);
		
		return dto;
	}
	
	
	public TipoMiembro getTipoMiembro() {
		return tipoMiembro;
	}


	public void setTipoMiembro(TipoMiembro tipoMiembro) {
		this.tipoMiembro = tipoMiembro;
	}


	public boolean tenesMiembro(JugadorDTO jugador) {
		
		return this.jugador.getApodo().equals(jugador.getApodo());
	}

	public RankingDTO obtenerRanking() {
		return this.ranking.toDTO();
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

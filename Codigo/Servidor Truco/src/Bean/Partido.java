package Bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import DTO.PartidoDTO;

/**
 * Partido esta compuesto por varios chicos. 
 * 
 * Va a ser el controladorDelJuego, es decir, quien va a jugar, que cosas se cantaron, conoce todo.
**/

@Entity
@Table (name = "Partidos")
public class Partido {
	@Id 
	@Column (name = "id_partido", nullable = false)
	private int id;
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_partido")
	private List<Pareja> parejas;
	
	@Column
	private int parejaGanadora;
	@Column (name = "fecha_inicio")
	private Timestamp fechaInicio;
	@Column (name = "fecha_fin")
	private Timestamp fechaFin;
	@Column (columnDefinition = "int")
	private TipoPartido tipoPartido;
	@Column (columnDefinition = "int")
	private EstadoPartido estadoPartido;
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_partido")
	private List<Chico> chicos;
	
	
	public Partido() {
	}

	public Partido(int idPartida, ArrayList<Pareja> parejas, int parejaGanadora,
			Timestamp fechaInicio, Timestamp fechaFin, TipoPartido tipoPartido, EstadoPartido estadoPartido) {
		this.id = idPartida;
		this.chicos = new ArrayList<Chico>();
		this.parejas = parejas;
		this.parejaGanadora = parejaGanadora;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.tipoPartido = tipoPartido;
		this.estadoPartido = estadoPartido;
	}
	
	


	public List<Chico> getChicos() {
		return chicos;
	}

	public void setChicos(ArrayList<Chico> chicos) {
		this.chicos = chicos;
	}

	public List<Pareja> getParejas() {
		return parejas;
	}

	public void setParejas(ArrayList<Pareja> parejas) {
		this.parejas = parejas;
	}

	public int getParejaGanadora() {
		return parejaGanadora;
	}

	public void setParejaGanadora(int parejaGanadora) {
		this.parejaGanadora = parejaGanadora;
	}

	public Timestamp getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Timestamp getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}

	public TipoPartido getTipoPartido() {
		return tipoPartido;
	}

	public void setTipoPartido(TipoPartido tipoPartido) {
		this.tipoPartido = tipoPartido;
	}

	public EstadoPartido getEstadoPartido() {
		return estadoPartido;
	}

	public void setEstadoPartido(EstadoPartido estadoPartido) {
		this.estadoPartido = estadoPartido;
	}

	
	public boolean sosTipoPartido(TipoPartido tipo) {
		return false;
	}
	
	public boolean sosDelPeriodo(Date desde, Date hasta) {
		return false;
	}
	
	public boolean participoJugador(Jugador jugador) {
		return false;
	}
	
	public Jugador calcularResultadoEnvido() {
		return null;
	}
	
	public Chico obtenerChicoActivo() {
		return null;
	}
	
	public void actualizarRankingJugadores() {
	
	}
	
	public PartidoDTO toDTO() {
		return null;
	}
	
	public boolean estasTerminado() {
		return false;
	}
	
	public boolean sosPartido(PartidoDTO partio) {
		return false;
	}
}

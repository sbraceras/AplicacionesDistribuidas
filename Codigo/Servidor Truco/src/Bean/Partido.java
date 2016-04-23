package Bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import DTO.PartidoDTO;

/**
 * Partido esta compuesto por varios chicos. 
 * 
 * Va a ser el controladorDelJuego, es decir, quien va a jugar, que cosas se cantaron, conoce todo.
**/
public class Partido {
	private ArrayList<Pareja> parejas;
	private int parejaGanadora;
	private Timestamp fechaInicio;
	private Timestamp fechaFin;
	private TipoPartido tipoPartido;
	private EstadoPartido estadoPartido;
	
	
	public Partido() {
	}

	public Partido(int idPartida, ArrayList<Chico> chicos, ArrayList<Pareja> parejas, int parejaGanadora,
			Timestamp fechaInicio, Timestamp fechaFin, TipoPartido tipoPartido, EstadoPartido estadoPartido) {
		this.idPartida = idPartida;
		this.chicos = chicos;
		this.parejas = parejas;
		this.parejaGanadora = parejaGanadora;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.tipoPartido = tipoPartido;
		this.estadoPartido = estadoPartido;
	}

	private int idPartida;
	private ArrayList<Chico> chicos;
	public int getIdPartida() {
		return idPartida;
	}

	public void setIdPartida(int idPartida) {
		this.idPartida = idPartida;
	}

	public ArrayList<Chico> getChicos() {
		return chicos;
	}

	public void setChicos(ArrayList<Chico> chicos) {
		this.chicos = chicos;
	}

	public ArrayList<Pareja> getParejas() {
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
	
	}
	
	public Jugador calcularResultadoEnvido() {
	
	}
	
	public Chico obtenerChicoActivo() {
	
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

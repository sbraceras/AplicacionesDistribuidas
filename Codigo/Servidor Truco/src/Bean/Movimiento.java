package Bean;

import java.sql.Timestamp;

import DTO.MovimientoDTO;

public class Movimiento {
	protected int id;
	protected int numeroTurno;
	protected Timestamp fechaHora;
	protected MovimientoDTO toDTO() {
		return null;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumeroTurno() {
		return numeroTurno;
	}
	public void setNumeroTurno(int numeroTurno) {
		this.numeroTurno = numeroTurno;
	}
	public Timestamp getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(Timestamp fechaHora) {
		this.fechaHora = fechaHora;
	}
}

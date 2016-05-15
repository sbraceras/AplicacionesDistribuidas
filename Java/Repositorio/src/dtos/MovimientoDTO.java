package dtos;

import java.sql.Timestamp;




public class MovimientoDTO {

	
	
	protected int id;
	protected int numeroTurno;
	protected Timestamp fechaHora;
	
	public MovimientoDTO() {
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

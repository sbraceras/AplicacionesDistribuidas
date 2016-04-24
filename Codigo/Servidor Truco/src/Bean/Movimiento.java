package Bean;

import java.sql.Timestamp;

import javax.persistence.*;

import DTO.MovimientoDTO;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn( name="tipo",discriminatorType=DiscriminatorType.STRING)
@Entity
public class Movimiento {
	
	@Id
	@Column (name = "id_movimiento", nullable = false)
	protected int id;
	@Column (name = "nro_turno")
	protected int numeroTurno;
	@Column (name = "fecha_hora")
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

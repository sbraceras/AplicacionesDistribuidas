package Bean;

import javax.persistence.*;

import DTO.ParejaDTO;
import ENUMS.TipoCategoria;

/**
 * Si no tiene asignado un numero pareja no esta en un partido
**/

@Entity
@Table (name = "Parejas")
public class Pareja {
	@Id
	@Column (name = "id_pareja", nullable = false)
	private int id;
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_jugador1")
	private Jugador jugador1;
	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_jugador2")
	private Jugador jugador2;
	@Column (name = "nro_Pareja")
	private int numeroPareja;
	
	public TipoCategoria obtenerCategoriaSuperior() { //ver como hacer enumeration
	
		return null;
	}
	
	public Pareja() {
	}

	public Pareja(Jugador jugador1, Jugador jugador2) {
	
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
		
	}
	
	public ParejaDTO toDTO (){
		
		ParejaDTO dto = new ParejaDTO();
		dto.setId(this.id);
		dto.setJugador1(this.jugador1.toDTO());
		dto.setJugador2(this.jugador2.toDTO());
		dto.setNumeroPareja(this.numeroPareja);
		return dto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Jugador getJugador1() {
		return jugador1;
	}

	public void setJugador1(Jugador jugador1) {
		this.jugador1 = jugador1;
	}

	public Jugador getJugador2() {
		return jugador2;
	}

	public void setJugador2(Jugador jugador2) {
		this.jugador2 = jugador2;
	}

	public int getNumeroPareja() {
		return numeroPareja;
	}

	public void setNumeroPareja(int numeroPareja) {
		this.numeroPareja = numeroPareja;
	}

	private boolean tenesJugador(Jugador jugador) {
	
		if(jugador1.getApodo().equals(jugador.getApodo()))
			return true;
		else
			if(jugador2.getApodo().equals(jugador.getApodo()))
				return true;
		return false;
	}
}

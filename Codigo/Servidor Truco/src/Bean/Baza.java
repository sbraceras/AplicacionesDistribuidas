package Bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import DTO.BazaDTO;

/**
 * La baza es cuando uno arroja 1 carta de las 3 que posee
**/

@Entity
@Table (name = "Bazas")
public class Baza {
	
	@Id
	@Column (name = "id_baza", nullable = false)
	private int id;
	
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_baza")
	private List<Movimiento> turnosBaza;
	
	@Column (name = "nro_baza")
	private int numeroBaza;
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_jugador")
	private Jugador ganador;
	
	/* No se persiste el orden de juego */
	private ArrayList<Jugador> ordenJuego;
	
	
	public Baza(int id, int numeroBaza) {
		this.id = id;
		this.turnosBaza = new ArrayList<Movimiento>();
		this.numeroBaza = numeroBaza;
		this.ordenJuego = new ArrayList<Jugador>();
	}

	public Baza() {
	}
	
	
	
	public Jugador obtenerGanador() {
		return null;
	}
	
	public BazaDTO toDTO() {
		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Movimiento> getTurnosBaza() {
		return turnosBaza;
	}

	public void setTurnosBaza(ArrayList<Movimiento> turnosBaza) {
		this.turnosBaza = turnosBaza;
	}

	public int getNumeroBaza() {
		return numeroBaza;
	}

	public void setNumeroBaza(int numeroBaza) {
		this.numeroBaza = numeroBaza;
	}

	public Jugador getGanador() {
		return ganador;
	}

	public void setGanador(Jugador ganador) {
		this.ganador = ganador;
	}

	public ArrayList<Jugador> getOrdenJuego() {
		return ordenJuego;
	}

	public void setOrdenJuego(ArrayList<Jugador> ordenJuego) {
		this.ordenJuego = ordenJuego;
	}


	
	
}

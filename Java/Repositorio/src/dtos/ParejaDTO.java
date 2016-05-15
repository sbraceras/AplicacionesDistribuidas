package dtos;

import java.io.Serializable;

public class ParejaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private JugadorDTO jugador1;
	private JugadorDTO jugador2;
	private int numeroPareja;

	public ParejaDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public JugadorDTO getJugador1() {
		return jugador1;
	}

	public void setJugador1(JugadorDTO jugador1) {
		this.jugador1 = jugador1;
	}

	public JugadorDTO getJugador2() {
		return jugador2;
	}

	public void setJugador2(JugadorDTO jugador2) {
		this.jugador2 = jugador2;
	}

	public int getNumeroPareja() {
		return numeroPareja;
	}

	public void setNumeroPareja(int numeroPareja) {
		this.numeroPareja = numeroPareja;
	}

}

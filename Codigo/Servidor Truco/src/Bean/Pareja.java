package Bean;

/**
 * Si no tiene asignado un numero pareja no esta en un partido
**/
public class Pareja {
	private int id;
	private Jugador jugador1;
	private Jugador jugador2;
	private int numeroPareja;
	public TipoCategoria obtenerCategoriaSuperior() { //ver como hacer enumeration
	
		return null;
	}
	
	public Pareja() {
	}

	public Pareja(int id, Jugador jugador1, Jugador jugador2, int numeroPareja) {
		this.id = id;
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
		this.numeroPareja = numeroPareja;
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
	
		return false;
	}
}

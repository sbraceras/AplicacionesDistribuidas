package Bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import DTO.ChicoDTO;

/**
 * Chico es la partida corta de 15 puntos
**/


@Entity
@Table (name = "Chicos")
public class Chico {
	
	@Id
	@Column (name = "id_chico", nullable = false)
	private int id;
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_chico")
	private List<Mano> manos;
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_chico")
	private List<PuntajePareja> puntajes;
	@Column (name = "puntaje_maximo")
	private int puntajeMaximo;
	
	
	public Chico() {
	}

	public Chico(int id, int puntajeMaximo) {
		this.id = id;
		this.manos = new ArrayList<Mano>();
		this.puntajes = new ArrayList<PuntajePareja>();
		this.puntajeMaximo = puntajeMaximo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Mano> getManos() {
		return manos;
	}

	public void setManos(ArrayList<Mano> manos) {
		this.manos = manos;
	}

	public List<PuntajePareja> getPuntajes() {
		return puntajes;
	}

	public void setPuntajes(ArrayList<PuntajePareja> puntajes) {
		this.puntajes = puntajes;
	}

	public int getPuntajeMaximo() {
		return puntajeMaximo;
	}

	public void setPuntajeMaximo(int puntajeMaximo) {
		this.puntajeMaximo = puntajeMaximo;
	}

	public Mano obtenerUltimaMano() {
		return null;///////
	}
	
	public void actualizarPuntajePareja(int puntaje, Pareja pareja) {
	
	}
	
	public void nuevaMano(ArrayList<Jugador> ordenJuego, int numeroMano) {
	
	}
	
	public ChicoDTO toDTO() {
		return null;//////
	}
}

package Bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import DTO.RankingDTO;


@Entity
@Table (name = "Grupos")
public class Grupo {
	
	@Id
	@Column (name = "id_grupo", nullable = false)
	private int id;
	@Column
	private String nombre;
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_grupo")
	private List<MiembroGrupo> miembros;
	
	
	/* Las Parejas Activas no se persisten */
	@Transient
	private List<Pareja> parejasActivas;
	
	
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_grupo")
	private List<Partido> partidos;
	
	
	public Grupo() {
		
		parejasActivas = new ArrayList<Pareja>();
	}

	public Grupo(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
		this.miembros = new ArrayList<MiembroGrupo>();
		this.parejasActivas = new ArrayList<Pareja>();
		this.partidos = new ArrayList<Partido>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<MiembroGrupo> getMiembros() {
		return miembros;
	}

	public void setMiembros(ArrayList<MiembroGrupo> miembros) {
		this.miembros = miembros;
	}

	public List<Pareja> getParejasActivas() {
		return parejasActivas;
	}

	public void setParejasActivas(ArrayList<Pareja> parejasActivas) {
		this.parejasActivas = parejasActivas;
	}

	public List<Partido> getPartidos() {
		return partidos;
	}

	public void setPartidos(ArrayList<Partido> partidos) {
		this.partidos = partidos;
	}

	public void armarPareja(ArrayList<Jugador> integrantes) {
	
	}
	
	public void crearPartida(ArrayList<Pareja> parejas) {
	
	}
	
	public void actualizarRankings() {
	
	}
	
	public void eliminarMiembroGrupo(Jugador jugador) {
	
	}
	
	public ArrayList<RankingDTO> obtenerRanking() {
		return null;
	}
	
	public boolean esAdministrador(Jugador jugador) {
		return false;
	}
	
	public boolean sosGrupo(String nombre) {
		return false;
	}
	
	public void agregarMiembro(Jugador jugador) {
	
	}
	
	public void armarPartido(ArrayList<Pareja> parejas) {
	
	}
	
	public ArrayList<RankingDTO> obtenerRankingsDelGrupo() {
	
		return null;
		
	}
}

package Bean;

import java.util.ArrayList;

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
	private ArrayList<MiembroGrupo> miembros;
	/* Las Parejas Activas no se persisten */
	private ArrayList<Pareja> parejasActivas;
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_grupo")
	private ArrayList<Partido> partidos;
	
	
	public Grupo() {
	}

	public Grupo(int id, String nombre, ArrayList<MiembroGrupo> miembros, ArrayList<Pareja> parejasActivas,
			ArrayList<Partido> partidos) {
		this.id = id;
		this.nombre = nombre;
		this.miembros = miembros;
		this.parejasActivas = parejasActivas;
		this.partidos = partidos;
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

	public ArrayList<MiembroGrupo> getMiembros() {
		return miembros;
	}

	public void setMiembros(ArrayList<MiembroGrupo> miembros) {
		this.miembros = miembros;
	}

	public ArrayList<Pareja> getParejasActivas() {
		return parejasActivas;
	}

	public void setParejasActivas(ArrayList<Pareja> parejasActivas) {
		this.parejasActivas = parejasActivas;
	}

	public ArrayList<Partido> getPartidos() {
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
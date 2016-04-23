package Bean;

import java.util.ArrayList;

import javax.persistence.*;

import DTO.GrupoDTO;
import DTO.JugadorDTO;
import DTO.RankingDTO;

@Entity
@Table (name = "Jugadores")
public class Jugador {
	
	@Id
	@Column (name = "id_jugador", nullable = false)
	private int id;
	@Column
	private String apodo;
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_jugador")
	private Ranking ranking;
	@Column (columnDefinition = "varchar(50)")
	private String mail;
	@Column (name = "clave", columnDefinition = "varchar(50)")
	private String password;
	@Column (columnDefinition = "tinyint")
	private TipoCategoria categoria;
	@ManyToMany (cascade = CascadeType.ALL)
	@JoinTable (name = "Grupo_Jugador",
	joinColumns = {@JoinColumn (name = "id_jugador")},
	inverseJoinColumns = {@JoinColumn (name = "id_grupo")})	
	private ArrayList<Grupo> grupos;
	
	
	
	public Jugador() {
	}

	public Jugador(int id, String apodo, Ranking ranking, String mail, String password, TipoCategoria categoria,
			ArrayList<Grupo> grupos) {
		this.id = id;
		this.apodo = apodo;
		this.ranking = ranking;
		this.mail = mail;
		this.password = password;
		this.categoria = categoria;
		this.grupos = grupos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApodo() {
		return apodo;
	}

	public void setApodo(String apodo) {
		this.apodo = apodo;
	}

	public Ranking getRanking() {
		return ranking;
	}

	public void setRanking(Ranking ranking) {
		this.ranking = ranking;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TipoCategoria getCategoria() {
		return categoria;
	}

	public void setCategoria(TipoCategoria categoria) {
		this.categoria = categoria;
	}

	public ArrayList<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(ArrayList<Grupo> grupos) {
		this.grupos = grupos;
	}

	public boolean sosJugador(JugadorDTO jugador) {
	
	}
	
	public boolean contraseñaCorrecta(String contraseña) {
	
	}
	
	public TipoCategoria cambiarCategoria() { ////////VER ENUMERATION
	
	}
	
	public void actualizarRanking(int puntos, Partido partido) {
	
	}
	
	public Grupo obtenerGrupo(GrupoDTO grupo) {
		return null;
	}
	
	public RankingDTO obtenerRanking() {
	
	}
}

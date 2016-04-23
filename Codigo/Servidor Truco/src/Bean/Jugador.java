package Bean;

import java.util.ArrayList;

import DTO.GrupoDTO;
import DTO.JugadorDTO;
import DTO.RankingDTO;

public class Jugador {
	private int id;
	private String apodo;
	private Ranking ranking;
	private String mail;
	private String password;
	private TipoCategoria categoria;  ///ver como hacer enumeration
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

package bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import daos.JugadorDAO;
import dtos.GrupoDTO;
import dtos.JugadorDTO;
import dtos.RankingDTO;
import enums.TipoCategoria;

@Entity
@Table(name = "Jugadores")
public class Jugador {

	@Id
	@Column(name = "id_jugador", nullable = false)
	@GeneratedValue
	private int id;
	@Column
	private String apodo;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_ranking")
	private Ranking ranking;

	@Column(columnDefinition = "varchar(50)")
	private String mail;
	@Column(name = "clave", columnDefinition = "varchar(50)")
	private String password;
	@Column(columnDefinition = "tinyint")
	private TipoCategoria categoria;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Grupo_Jugador",
		joinColumns = { @JoinColumn(name = "id_jugador") },
		inverseJoinColumns = { @JoinColumn(name = "id_grupo") })
	private List<Grupo> grupos;

	public Jugador() {
	}

	public Jugador(String apodo, String mail, String password) {
		this.apodo = apodo;
		this.ranking = new Ranking();
		this.mail = mail;
		this.password = password;
		this.categoria = TipoCategoria.Novato;
		this.grupos = new ArrayList<Grupo>();
	}

	public JugadorDTO toDTO() {
		JugadorDTO dto = new JugadorDTO();

		dto.setId(this.id);
		dto.setApodo(this.apodo);
		dto.setCategoria(this.categoria);
		ArrayList<GrupoDTO> gruposDto = new ArrayList<GrupoDTO>();

		for (int i = 0; i < grupos.size(); i++) {
			gruposDto.add(grupos.get(i).toDto());
		}

		dto.setGrupos(gruposDto);
		dto.setMail(this.mail);
		dto.setPassword(this.password);
		dto.setRanking(this.ranking.toDTO());
		return dto;
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

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(ArrayList<Grupo> arrayList) {
		this.grupos = arrayList;
	}

	public boolean sosJugador(JugadorDTO jugador) {
		return jugador.getId() == this.id;
	}

	public boolean contraseñaCorrecta(String contraseña) {
		return this.password == contraseña;
	}

	public void cambiarCategoria(TipoCategoria tipo) { // //////VER ENUMERATION
		categoria = tipo;
	}

	public void actualizarRanking(int puntos, Partido partido) {

	}

	public Grupo obtenerGrupo(GrupoDTO grupo) {
		for (int i = 0; i < grupos.size(); i++) {

			if (grupos.get(i).getNombre().equals(grupo.getNombre()))
				return grupos.get(i);
		}

		/* No esta en memoria, levanto lo de la base de datos */

		grupos = JugadorDAO.getinstance().obtenerGruposJugador(this);
		for (int i = 0; i < grupos.size(); i++) {

			if (grupos.get(i).getNombre().equals(grupo.getNombre()))
				return grupos.get(i);
		}

		return null;
	}

	public RankingDTO obtenerRanking() {
		return this.ranking.toDTO();
	}

}

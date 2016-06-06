package bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	//@LazyCollection (LazyCollectionOption.FALSE)
	private Ranking ranking;
	
	
	
	
	@Column(columnDefinition = "varchar(50)")
	private String mail;
	@Column(name = "clave", columnDefinition = "varchar(50)")
	private String password;
	@Column(columnDefinition = "tinyint")
	private TipoCategoria categoria;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "Grupo_Jugador",
		joinColumns = { @JoinColumn(name = "id_jugador") },
		inverseJoinColumns = { @JoinColumn(name = "id_grupo") })
//	@LazyCollection (LazyCollectionOption.FALSE)
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

		getGrupos();
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
		grupos = JugadorDAO.getinstance().obtenerGruposJugador(this);
		return grupos;
	}

	public void setGrupos(ArrayList<Grupo> arrayList) {
		this.grupos = arrayList;
	}

	public boolean sosJugador(JugadorDTO jugador) {
		return (this.id == jugador.getId());
	}

	public boolean contrasenaCorrecta(String contrasena) {
		return this.password == contrasena;
	}

	public void cambiarCategoria(TipoCategoria tipo) {
		categoria = tipo;
	}

	public void actualizarRanking(int puntos, Partido partido) {

		ranking.actualizar(partido, puntos);
		
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
	
	public void agregarGrupo(Grupo grupo){
		getGrupos();
		grupos.add(grupo);
		JugadorDAO.getinstance().guardarJugador(this);
	}

	public void actualizarRankingMiembro(Partido partido, int puntos) {
		
		for(Grupo grupo: grupos){
			
			if(grupo.tenesPartido(partido) == true)
				grupo.actualizarRanking(this, puntos, partido);
			
		}
		
	}

}

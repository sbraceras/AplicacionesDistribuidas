package DTO;

import java.util.List;


import ENUMS.TipoCategoria;

public class JugadorDTO {

	
	private int id;
	private String apodo;
	private RankingDTO ranking;
	private String mail;
	private String password;
	private TipoCategoria categoria;
	private List<GrupoDTO> grupos;
	
	public JugadorDTO() {
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

	public RankingDTO getRanking() {
		return ranking;
	}

	public void setRanking(RankingDTO ranking) {
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

	public List<GrupoDTO> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<GrupoDTO> grupos) {
		this.grupos = grupos;
	}
	
	
}

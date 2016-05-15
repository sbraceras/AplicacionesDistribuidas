package dtos;

import java.util.List;


public class GrupoDTO {

	private int id;
	private String nombre;
	private List<MiembroGrupoDTO> miembros;
	private List<ParejaDTO> parejasActivas;
	private List<PartidoDTO> partidos;
	
	
	public GrupoDTO() {
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


	public List<MiembroGrupoDTO> getMiembros() {
		return miembros;
	}


	public void setMiembros(List<MiembroGrupoDTO> miembros) {
		this.miembros = miembros;
	}


	public List<ParejaDTO> getParejasActivas() {
		return parejasActivas;
	}


	public void setParejasActivas(List<ParejaDTO> parejasActivas) {
		this.parejasActivas = parejasActivas;
	}


	public List<PartidoDTO> getPartidos() {
		return partidos;
	}


	public void setPartidos(List<PartidoDTO> partidos) {
		this.partidos = partidos;
	}

	
	
}

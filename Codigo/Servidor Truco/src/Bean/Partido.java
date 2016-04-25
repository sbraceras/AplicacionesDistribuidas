package Bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import DTO.ChicoDTO;
import DTO.ParejaDTO;
import DTO.PartidoDTO;
import ENUMS.EstadoPartido;
import ENUMS.TipoPartido;

/**
 * Partido esta compuesto por varios chicos. 
 * 
 * Va a ser el controladorDelJuego, es decir, quien va a jugar, que cosas se cantaron, conoce todo.
**/

@Entity
@Table (name = "Partidos")
public class Partido {
	@Id 
	@Column (name = "id_partido", nullable = false)
	private int id;
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_partido")
	private List<Pareja> parejas;
	
	@Column
	private int parejaGanadora;
	@Column (name = "fecha_inicio")
	private Timestamp fechaInicio;
	@Column (name = "fecha_fin")
	private Timestamp fechaFin;
	@Column (columnDefinition = "int")
	private TipoPartido tipoPartido;
	@Column (columnDefinition = "int")
	private EstadoPartido estadoPartido;
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_partido")
	private List<Chico> chicos;
	
	
	public Partido() {
	}

	public Partido(ArrayList<Pareja> parejas, Timestamp fechaInicio, TipoPartido tipoPartido) {
		
		this.chicos = new ArrayList<Chico>();
		this.parejas = parejas;
		this.fechaInicio = fechaInicio;
		this.tipoPartido = tipoPartido;
		this.estadoPartido = EstadoPartido.Abierto;
		this.fechaFin = null;
	}
	
	
	public PartidoDTO toDTO (){
		
		PartidoDTO dto = new PartidoDTO();
		List<ChicoDTO> chicosDto = new ArrayList<ChicoDTO>();
		for(int i=0; i<chicos.size(); i++){
			chicosDto.add(chicos.get(i).toDto());
			
		}
		
		dto.setEstadoPartido(this.estadoPartido);
		
		dto.setFechaFin(this.fechaFin);
		dto.setFechaInicio(this.fechaInicio);
		dto.setId(this.id);
		dto.setParejaGanadora(this.parejaGanadora);
		dto.setTipoPartido(this.tipoPartido);
		List<ParejaDTO> parejasDto = new ArrayList<ParejaDTO>();
		
		for(int i=0; i<parejas.size();i++){
			parejasDto.add(parejas.get(i).toDTO());
		}
		dto.setChicos(chicosDto);
		dto.setParejas(parejasDto);
		
		return dto;
	}
	
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setParejas(List<Pareja> parejas) {
		this.parejas = parejas;
	}

	public void setChicos(List<Chico> chicos) {
		this.chicos = chicos;
	}

	public List<Chico> getChicos() {
		return chicos;
	}

	public void setChicos(ArrayList<Chico> chicos) {
		this.chicos = chicos;
	}

	public List<Pareja> getParejas() {
		return parejas;
	}

	public void setParejas(ArrayList<Pareja> parejas) {
		this.parejas = parejas;
	}

	public int getParejaGanadora() {
		return parejaGanadora;
	}

	public void setParejaGanadora(int parejaGanadora) {
		this.parejaGanadora = parejaGanadora;
	}

	public Timestamp getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Timestamp getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}

	public TipoPartido getTipoPartido() {
		return tipoPartido;
	}

	public void setTipoPartido(TipoPartido tipoPartido) {
		this.tipoPartido = tipoPartido;
	}

	public EstadoPartido getEstadoPartido() {
		return estadoPartido;
	}

	public void setEstadoPartido(EstadoPartido estadoPartido) {
		this.estadoPartido = estadoPartido;
	}

	
	public boolean sosTipoPartido(TipoPartido tipo) {
		return tipo==this.tipoPartido;
	}
	
	public boolean sosDelPeriodo(Date desde, Date hasta) {
		
		if(this.fechaInicio.equals(desde))
			return true;
		else
			if(this.fechaInicio.after(desde))
			{
				if(this.fechaInicio.equals(hasta))
					return true;
				else
					if(this.fechaFin.before(hasta))
						return true;
			}
		return false;
	}
	
	public boolean participoJugador(Jugador jugador) {
		
		for(int i=0; i<parejas.size();i++){
			
			if(parejas.get(i).getJugador1().getApodo().equals(jugador.getApodo()))
				return true;
			if(parejas.get(i).getJugador2().getApodo().equals(jugador.getApodo()))
				return true;
		}
		return false;
	}
	
/*
	
	Desarrollarrrrrrrrrrrrrrrrrr
	
	////////////////
	//////////////////
	*/////
	
	public Jugador calcularResultadoEnvido() {
		return null;
	}
	
	
	
	public Chico obtenerChicoActivo() {
		
		Chico aux=null;
		
		for(int i=0; i<chicos.size();i++)
		{
			if(chicos.get(i).isTerminado()==false)
				aux= chicos.get(i);
		}
		return aux;
	}
	
	
	/* DESARROLLAR *////////////////////////////////
	////////////////////////////////////////////////
	/////////////////////////////////////////////////
	///////////////////////////////
	
	public void actualizarRankingJugadores() {
	
	}
	
	
	public boolean estasTerminado() {
		
		if(estadoPartido.equals(EstadoPartido.Cerrado))
			return true;
		return false;
	}
	
	public boolean sosPartido(PartidoDTO partido) {

		return partido.getId()==this.id;
	}
}

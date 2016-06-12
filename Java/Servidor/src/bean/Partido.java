package bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import daos.PartidoDAO;
import dtos.*;
import enums.*;
import exceptions.BazaException;
import exceptions.PartidoException;


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
	@GeneratedValue
	private int id;

	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn (name = "id_partido")
	private List<Pareja> parejas;
	
	@OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn (name = "id_pareja")
	private Pareja parejaGanadora;
	@Column (name = "fecha_inicio")
	private Timestamp fechaInicio;
	@Column (name = "fecha_fin")
	private Timestamp fechaFin;
	@Column (columnDefinition = "int")
	private TipoPartido tipoPartido;
	@Column (columnDefinition = "int")
	private EstadoPartido estadoPartido;
	
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn (name = "id_partido")
	private List<Chico> chicos;
	
	
	public Partido() {
	}

	public Partido(List<Pareja> parejas, Timestamp fechaInicio, TipoPartido tipoPartido) {
		this.chicos = new ArrayList<Chico>();
		this.parejas = parejas;
		this.parejaGanadora = null;
		this.fechaInicio = fechaInicio;
		this.tipoPartido = tipoPartido;
		this.estadoPartido = EstadoPartido.Empezado;
		this.fechaFin = null;
		this.fechaInicio = fechaInicio;

		this.chicos.add(new Chico(this,1, 30, this.parejas));
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
		if(parejaGanadora != null)
			dto.setParejaGanadora(this.parejaGanadora.toDTO());
		else
			dto.setParejaGanadora(null);
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

	public Pareja getParejaGanadora() {
		return parejaGanadora;
	}

	public void setParejaGanadora(Pareja parejaGanadora) {
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
		
			if(parejas.get(i).tenesJugador(jugador))
				return true;
		}
		return false;
	}

	public Chico obtenerChicoActivo() {
		Chico aux = null;

		for (int i=0; i<chicos.size(); i++) {
			if (chicos.get(i).isTerminado() == false)
				aux = chicos.get(i);
		}
		return aux;
	}

	public void actualizarRankingJugadores() {
		Pareja perdedora;
		if(parejas.get(0).esPareja(parejaGanadora))
			perdedora = parejas.get(1);
		else
			perdedora= parejas.get(0);
		
		
		if (tipoPartido.equals(TipoPartido.Grupo)) {
			parejaGanadora.getJugador1().actualizarRanking(5, this);
			parejaGanadora.getJugador2().actualizarRanking(5, this);
			
			/*Actualizar Ranking de los MiembrosGrupo*/
			
			parejaGanadora.getJugador1().actualizarRankingMiembro(this, 5);
			parejaGanadora.getJugador2().actualizarRankingMiembro(this, 5);
			
			perdedora.getJugador1().actualizarRankingMiembro(this, 0);
			perdedora.getJugador2().actualizarRankingMiembro(this, 0);

		} else {
			TipoCategoria categoriaOponente = perdedora.obtenerCategoriaSuperior();
			
			if(parejaGanadora.getJugador1().getCategoria().ordinal()<categoriaOponente.ordinal()) //el jugador 1 es inferior
				parejaGanadora.getJugador1().actualizarRanking(12, this);
			else
				parejaGanadora.getJugador1().actualizarRanking(10, this);
			
			if(parejaGanadora.getJugador2().getCategoria().ordinal()<categoriaOponente.ordinal()) //el jugador 2 es inferior
				parejaGanadora.getJugador2().actualizarRanking(12, this);
			else
				parejaGanadora.getJugador2().actualizarRanking(10, this);
		}
		
		perdedora.getJugador1().actualizarRanking(0, this);
		perdedora.getJugador2().actualizarRanking(0, this);
	}

	public boolean estasTerminado() {		
		return (estadoPartido.equals(EstadoPartido.Terminado));
	}

	public boolean sosPartido(PartidoDTO partido) {
		return partido.getId() == this.id;
	}

	public void cerrarChico () throws PartidoException{
		int  chicosGanadosPareja1 =0;
		int  chicosGanadosPareja2 =0;
		
		if(estadoPartido != EstadoPartido.Terminado){
			Pareja ganadoraChico;
			for(Chico chico: chicos){
				ganadoraChico = chico.obtenerParejaGanadora();
				if(parejas.get(0).esPareja(ganadoraChico))
						chicosGanadosPareja1++;
					else
						chicosGanadosPareja2++;
				}
			}
			
			if(chicosGanadosPareja1 == 2 || chicosGanadosPareja2 == 2) {
				fechaFin = (Timestamp) new Date();
				estadoPartido = EstadoPartido.Terminado;

				if(chicosGanadosPareja1 == 2) {
					parejaGanadora = parejas.get(0);
				} else
					parejaGanadora = parejas.get(1);

				actualizarRankingJugadores();

				PartidoDAO.getInstance().guardarPartido(this);
			} else
			{
				//No se termino el partido, aun faltan chicos por jugar
				chicos.add(new Chico(this, chicos.size()+1, 30, this.parejas));								
			}
	}
	

	public JugadorDTO turnoCartaJugador (){
		if(estadoPartido != EstadoPartido.Terminado) {
			Chico chico = obtenerChicoActivo();

			if(chico.tocaCarta() == true){
				return chico.obtenerTurnoJugador().toDTO();
			}
		}
		
		return null;
	}

	public void nuevoMovimiento(Jugador jugador, Movimiento movimiento) throws PartidoException, BazaException {
		if (!this.estasTerminado()) {
			// deberia haber un Chico activo! 
			Chico chico = obtenerChicoActivo();
			chico.agregarMovimiento(jugador, movimiento);
		}
	}

	public List<TipoEnvite> obtenerEnvitesPosibles() {
		return obtenerChicoActivo().obtenerUltimaMano().obtenerEnvitesPosibles();
	}

	public void levantar() {
		
		for(Chico chico: chicos){
			chico.levantar(this);
		}
		
	}

	

}

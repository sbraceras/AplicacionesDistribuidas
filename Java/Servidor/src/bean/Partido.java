package bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import daos.PartidoDAO;
import dtos.*;
import enums.*;
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
	
	@OneToMany (cascade = CascadeType.ALL) /* fetch = FetchType.EAGER)*/
	@JoinColumn (name = "id_partido")
	private List<Chico> chicos;
	
	
	public Partido() {
	}

	public Partido(List<Pareja> parejas, Timestamp fechaInicio, TipoPartido tipoPartido) {
		this.chicos = new ArrayList<Chico>();
		this.parejas = parejas;
		this.fechaInicio = fechaInicio;
		this.tipoPartido = tipoPartido;
		this.estadoPartido = EstadoPartido.Empezado;
		this.fechaFin = null;
		this.fechaInicio = fechaInicio;

		this.chicos.add(new Chico(this, 30, this.parejas));
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
		Pareja ganadora = parejas.get(parejaGanadora);  /* considero que primer pareja es 0 y la segunda 1 */
		Pareja perdedora;
		if (parejaGanadora ==0) {
			perdedora = parejas.get(1);
		} else
			perdedora = parejas.get(0);
		
		
		if (tipoPartido.equals(TipoPartido.Grupo)) {
			ganadora.getJugador1().actualizarRanking(5, this);
			ganadora.getJugador2().actualizarRanking(5, this);
			
			/*Actualizar Ranking de los MiembrosGrupo*/
			
			ganadora.getJugador1().actualizarRankingMiembro(this, 5);
			ganadora.getJugador2().actualizarRankingMiembro(this, 5);
			
			perdedora.getJugador1().actualizarRankingMiembro(this, 0);
			perdedora.getJugador2().actualizarRankingMiembro(this, 0);

		} else {
			TipoCategoria categoriaOponente = perdedora.obtenerCategoriaSuperior();
			
			if(ganadora.getJugador1().getCategoria().ordinal()<categoriaOponente.ordinal()) //el jugador 1 es inferior
				ganadora.getJugador1().actualizarRanking(12, this);
			else
				ganadora.getJugador1().actualizarRanking(10, this);
			
			if(ganadora.getJugador2().getCategoria().ordinal()<categoriaOponente.ordinal()) //el jugador 2 es inferior
				ganadora.getJugador2().actualizarRanking(12, this);
			else
				ganadora.getJugador2().actualizarRanking(10, this);
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

	public void terminarPartido () throws PartidoException{
		int  chicosGanadosPareja1 =0;
		int  chicosGanadosPareja2 =0;
		
		if(estadoPartido != EstadoPartido.Terminado){
			Pareja ganadoraChico;
			for(Chico chico: chicos){
				ganadoraChico = chico.obtenerParejaGanadora();
				if(ganadoraChico == null) {
					throw new PartidoException("No se termina el partido, hay chicos activos");
				} else {
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
					parejaGanadora = 1;
				} else
					parejaGanadora = 2;

				actualizarRankingJugadores();

				PartidoDAO.getInstance().guardarPartido(this);
			} else
				throw new PartidoException("No se termino el partido, aun faltan chicos por jugar");
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

	public void nuevoMovimiento(Jugador jugador, Movimiento movimiento) {
		if (!this.estasTerminado()) {
			// deberia haber un Chico activo! 
			Chico chico = obtenerChicoActivo();
			chico.agregarMovimiento(jugador, movimiento);
		}
	}

	public List<TipoEnvite> obtenerEnvitesPosibles() {
		return obtenerChicoActivo().obtenerUltimaMano().obtenerEnvitesPosibles();
	}

}

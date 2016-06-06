package bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import dtos.BazaDTO;
import dtos.JugadorDTO;
import dtos.MovimientoDTO;


/**
 * La Baza es cada una de las 3 'rondas' que conforman la Mano.
 * En cada Baza, cada jugador arroja 1 sola carta. 
**/

@Entity
@Table (name = "Bazas")
public class Baza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "id_baza", nullable = false)
	private int id;
	
	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn (name = "id_baza")
	private List<Movimiento> turnosBaza;
	
	@Column (name = "nro_baza")
	private int numeroBaza;

	@OneToOne (cascade = CascadeType.ALL) /* fetch = FetchType.EAGER)*/
	@JoinColumn (name = "id_jugador")
	private Jugador ganador;
	
	@Transient
	/* No se persiste el orden de juego */
	private List<Jugador> ordenJuego;

	@Transient
	private int cantidadCartasTiradas;


	public Baza(int numeroBaza, List<Jugador> ordenJuego) {
		this.numeroBaza = numeroBaza;
		this.turnosBaza = new ArrayList<Movimiento>();
		this.ordenJuego = ordenJuego;
		this.cantidadCartasTiradas = 0;
	}

	public Baza() {
		
	}

	public Jugador obtenerGanador() {
		definirGanador();
		return ganador;
	}

	public BazaDTO toDTO() {
		BazaDTO dto = new BazaDTO();
		dto.setId(this.id);
		dto.setNumeroBaza(this.numeroBaza);
		ArrayList<JugadorDTO> orden = new ArrayList<JugadorDTO>();
		JugadorDTO jugador;
		for(int i=0; i<ordenJuego.size(); i++){
			
			jugador = ordenJuego.get(i).toDTO();
			orden.add(jugador);
		}
		dto.setOrdenJuego(orden);
		
		MovimientoDTO mov;
		ArrayList<MovimientoDTO> movimientos = new ArrayList<MovimientoDTO>();
		for(int i=0; i<turnosBaza.size(); i++) {
			mov = turnosBaza.get(i).toDTO();
			movimientos.add(mov);
		}
		dto.setTurnosBaza(movimientos);
		
		dto.setGanador(this.ganador.toDTO());
		
		return dto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Movimiento> getTurnosBaza() {
		return turnosBaza;
	}

	public void setTurnosBaza(ArrayList<Movimiento> turnosBaza) {
		this.turnosBaza = turnosBaza;
	}

	public int getNumeroBaza() {
		return numeroBaza;
	}

	public void setNumeroBaza(int numeroBaza) {
		this.numeroBaza = numeroBaza;
	}

	public void setGanador(Jugador ganador) {
		this.ganador = ganador;
	}

	public List<Jugador> getOrdenJuego() {
		return ordenJuego;
	}

	public void setOrdenJuego(List<Jugador> ordenJuego) {
		this.ordenJuego = ordenJuego;
	}
	
	public Movimiento obtenerUltimoMovimiento (){
		return turnosBaza.get(turnosBaza.size() - 1);
	}

	public Jugador obtenerTurnoBaza() {
		return ordenJuego.get(cantidadCartasTiradas); 
	}

	// devuelve el jugador que debe contestar el envite
	public Jugador obtenerTurnoContestar() {
		Movimiento aux = obtenerUltimoMovimiento(); // se sabe que es un envite

		if (((Envite) aux).getJugador().getId() == ordenJuego.get(2).getId()) {
			// el ultimo movimiento lo hizo el pie de la primer pareja,
			// por lo tanto debe contestar el pie de la segunda pareja.
			return ordenJuego.get(3);
		}
		// sino el ultimo canto lo hizo el pie de la segunda pareja,
		// por lo tanto debe contestar el pie de la primer pareja.
		return ordenJuego.get(2);
	}

/*

	public int obtenerCantidadCartasTiradas () {
		int cantidadTiradas = 0;

		for(Movimiento mov: turnosBaza) {
			if(mov instanceof CartaTirada)
				cantidadTiradas++;
		}
		return cantidadTiradas;
	}

*/

	public void definirGanador() {
		CartaJugador ganadora = null;

		for(Movimiento mov: turnosBaza) {
			if(mov instanceof CartaTirada) {
				if(ganadora == null) {
					ganadora = ((CartaTirada) mov).getCartaJugador();
				} else {
					if (ganadora.getCarta().getPosicionValor() >
					((CartaTirada) mov).getCartaJugador().getCarta().getPosicionValor())
						// significa que esta carta es mejor que la anterior
						ganadora = ((CartaTirada) mov).getCartaJugador();
				}
			}
		}
		ganador = ganadora.getJugador();
	}

	public int getCantidadCartasTiradas() {
		return cantidadCartasTiradas;
	}

	public Jugador getGanador() {
		return ganador;
	}

	public void agregarMovimiento(Jugador jugador, Movimiento movimiento) {
		turnosBaza.add(movimiento);

		if (movimiento instanceof CartaTirada) {
			cantidadCartasTiradas++;
		} else if (movimiento instanceof Envite) {
			
		}
	}

}

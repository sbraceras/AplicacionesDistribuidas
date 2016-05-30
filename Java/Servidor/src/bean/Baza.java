package bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import dtos.BazaDTO;
import dtos.JugadorDTO;
import dtos.MovimientoDTO;


/**
 * La baza es cuando uno arroja 1 carta de las 3 que posee
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
	private ArrayList<Jugador> ordenJuego;
	
	
	public Baza(int id, int numeroBaza) {
		this.id = id;
		this.turnosBaza = new ArrayList<Movimiento>();
		this.numeroBaza = numeroBaza;
		this.ordenJuego = new ArrayList<Jugador>();
	}

	public Baza() {
	}
	
	
	////* HACER BIEN */////
	public Jugador obtenerGanador() {
		return null;
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
		for(int i=0; i<turnosBaza.size(); i++)
		{
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

	public ArrayList<Jugador> getOrdenJuego() {
		return ordenJuego;
	}

	public void setOrdenJuego(ArrayList<Jugador> ordenJuego) {
		this.ordenJuego = ordenJuego;
	}
	
	public Movimiento obtenerUltimoMovimiento (){
		return turnosBaza.get(turnosBaza.size());
	}

	public Jugador obtenerTurnoBaza() {
		int cantidadTiradas = obtenerCantidadCartasTiradas();
		
		return ordenJuego.get(cantidadTiradas); 
		//si se tiraron 2 cartas le toca jugar al tercero, pero como es una colleccion
		//hay que restarle 1

		// PRECISAMENTE, SI SE TIRARON DOS Y LE TOCA JUGAR AL TERCERO, NO HAY QUE RESTARLE 1, NO?
	}
	
	
	public int obtenerCantidadCartasTiradas (){
		
		int cantidadTiradas = 0;
		
		for(Movimiento mov: turnosBaza){
			
			if(mov instanceof CartaTirada)
				cantidadTiradas++;
		}
		
		return cantidadTiradas;
	}

	
	public void definirGanador(List <Pareja> parejas){
		
		CartaJugador ganadora = null;
		
		for(Movimiento mov: turnosBaza){
			
			if(mov instanceof CartaTirada)
			{
				if(ganadora == null)
				{
					ganadora = ((CartaTirada) mov).getCartaJugador();
				}
				else
				{
					if(ganadora.getCarta().getPosicionValor()> ((CartaTirada) mov).getCartaJugador().getCarta().getPosicionValor())
						//significa que esta carta es mejor que la anterior
						ganadora = ((CartaTirada) mov).getCartaJugador();
				}
			}
				
		}
		
		ganador = ganadora.getJugador();
				
		}
	
	
	
	
}

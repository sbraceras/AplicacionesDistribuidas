package Bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import DTO.ChicoDTO;
import DTO.ManoDTO;
import DTO.ParejaDTO;
import DTO.PuntajeParejaDTO;

/**
 * Chico es la partida corta de 15 puntos
**/


@Entity
@Table (name = "Chicos")
public class Chico {
	
	@Id
	@Column (name = "id_chico", nullable = false)
	private int id;
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_chico")
	private List<Mano> manos;
	@OneToMany (cascade = CascadeType.ALL)
	@JoinColumn (name = "id_chico")
	private List<PuntajePareja> puntajes;
	@Column (name = "puntaje_maximo")
	private int puntajeMaximo;
	@Column (name = "nro_chico")
	private int numeroChico;
	@Column (columnDefinition = "bit")
	private boolean terminado;
	
	
	public Chico() {
	}

	public Chico(int id, int puntajeMaximo) {
		this.id = id;
		this.manos = new ArrayList<Mano>();
		this.puntajes = new ArrayList<PuntajePareja>();
		this.puntajeMaximo = puntajeMaximo;
	}
	

	public ChicoDTO toDto (){
		
		ChicoDTO dto = new ChicoDTO();
		dto.setId(this.id);
		dto.setPuntajeMaximo(this.puntajeMaximo);
		ArrayList<PuntajeParejaDTO> puntajesDto = new ArrayList<PuntajeParejaDTO>();
		for(int i=0; i<puntajes.size(); i++){
			puntajesDto.add(puntajes.get(i).toDTO());
		}
		dto.setPuntajes(puntajesDto);
		
		ArrayList<ManoDTO> manosDto = new ArrayList<ManoDTO>();
		for(int i=0; i<manos.size(); i++)
		{
			manosDto.add(manos.get(i).toDTO());
		}
		dto.setManos(manosDto);
		dto.setNumeroChico(this.numeroChico);
		dto.setTerminado(this.terminado);
		return dto;
		
		
	}
	
	
	
	public boolean isTerminado() {
		return terminado;
	}

	public void setTerminado(boolean terminado) {
		this.terminado = terminado;
	}

	public int getNumeroChico() {
		return numeroChico;
	}

	public void setNumeroChico(int numeroChico) {
		this.numeroChico = numeroChico;
	}

	public void setManos(List<Mano> manos) {
		this.manos = manos;
	}

	public void setPuntajes(List<PuntajePareja> puntajes) {
		this.puntajes = puntajes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Mano> getManos() {
		return manos;
	}

	public void setManos(ArrayList<Mano> manos) {
		this.manos = manos;
	}

	public List<PuntajePareja> getPuntajes() {
		return puntajes;
	}

	public void setPuntajes(ArrayList<PuntajePareja> puntajes) {
		this.puntajes = puntajes;
	}

	public int getPuntajeMaximo() {
		return puntajeMaximo;
	}

	public void setPuntajeMaximo(int puntajeMaximo) {
		this.puntajeMaximo = puntajeMaximo;
	}

	public Mano obtenerUltimaMano() {
		
		Mano mano = null;
		for(int i=0; i<manos.size(); i++)
		{
			if(mano==null)
			{
				mano = manos.get(i);
			}
			else
			{
				if(mano.getNumeroMano()<manos.get(i).getNumeroMano())
				{
					mano = manos.get(i);
				}
			}
		}
		return mano;
	}
	
	public void actualizarPuntajePareja(int puntaje, ParejaDTO pareja) {
	
				
		for(int i=0; i<puntajes.size(); i++){
			if(puntajes.get(i).getPareja().getNumeroPareja()==pareja.getNumeroPareja())
				puntajes.get(i).setPuntaje(puntaje);
		}
	}
	
	public void nuevaMano() {
	
		
		Mano anterior =obtenerUltimaMano();
		
		//Recalculo orden juego
		
		List<Jugador> ordenAnterior = anterior.getOrdenJuego();
		
		List<Jugador> ordenNuevo = new ArrayList<Jugador>();
		
		//obtengo el Ultimo
		Jugador jug = ordenAnterior.get(ordenAnterior.size());
		ordenNuevo.add(jug);
		//obtengo el resto
		for(int i=0; i<ordenAnterior.size()-1; i++)
		{
			ordenNuevo.add(ordenAnterior.get(i));
		}
		
		Mano nueva = new Mano(anterior.getNumeroMano()+1, ordenNuevo);
		manos.add(nueva);
	}
	
}

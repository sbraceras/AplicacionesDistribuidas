package Bean;

import javax.persistence.*;

import DTO.PuntajeParejaDTO;

@Entity
@Table (name = "PuntajesPareja")
public class PuntajePareja {
	@Id
	@Column (name = "id_puntaje", nullable = false)
	@GeneratedValue
	private int id;
	@OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn (name = "id_pareja")
	private Pareja pareja;
	@Column
	private int puntaje;
	
	
	public PuntajeParejaDTO toDTO(){
		
		PuntajeParejaDTO dto = new PuntajeParejaDTO();
		dto.setPareja(this.pareja.toDTO());
		dto.setPuntaje(this.puntaje);
		dto.setId(this.id);
		return dto;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Pareja getPareja() {
		return pareja;
	}
	public void setPareja(Pareja pareja) {
		this.pareja = pareja;
	}
	public int getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	public PuntajePareja() {
	}
	public PuntajePareja(Pareja pareja, int puntaje) {
		
		this.pareja = pareja;
		this.puntaje = puntaje;
	}
}

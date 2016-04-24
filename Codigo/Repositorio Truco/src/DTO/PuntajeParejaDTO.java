package DTO;



public class PuntajeParejaDTO {
	
	
	private int id;	
	private ParejaDTO pareja;
	private int puntaje;
	
	public PuntajeParejaDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ParejaDTO getPareja() {
		return pareja;
	}

	public void setPareja(ParejaDTO pareja) {
		this.pareja = pareja;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	
	
}

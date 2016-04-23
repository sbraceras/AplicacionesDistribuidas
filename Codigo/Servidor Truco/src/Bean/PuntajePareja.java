package Bean;

public class PuntajePareja {
	private int id;
	private Pareja pareja;
	private int puntaje;
	
	
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
	public PuntajePareja(int id, Pareja pareja, int puntaje) {
		this.id = id;
		this.pareja = pareja;
		this.puntaje = puntaje;
	}
}

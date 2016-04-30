package DTO;

import java.util.List;

public class MazoDTO {
	
	private List<CartaDTO> cartas;
	
	
	public MazoDTO(){
		
	}


	public List<CartaDTO> getCartas() {
		return cartas;
	}


	public void setCartas(List<CartaDTO> cartas) {
		this.cartas = cartas;
	}

	
}

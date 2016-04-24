package DTO;

import java.util.List;

public class ChicoDTO {


		private int id;
		private List<ManoDTO> manos;
		private List<PuntajeParejaDTO> puntajes;
		private int puntajeMaximo;
		
		public ChicoDTO() {
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public List<ManoDTO> getManos() {
			return manos;
		}

		public void setManos(List<ManoDTO> manos) {
			this.manos = manos;
		}

		public List<PuntajeParejaDTO> getPuntajes() {
			return puntajes;
		}

		public void setPuntajes(List<PuntajeParejaDTO> puntajes) {
			this.puntajes = puntajes;
		}

		public int getPuntajeMaximo() {
			return puntajeMaximo;
		}

		public void setPuntajeMaximo(int puntajeMaximo) {
			this.puntajeMaximo = puntajeMaximo;
		}
		
		
}

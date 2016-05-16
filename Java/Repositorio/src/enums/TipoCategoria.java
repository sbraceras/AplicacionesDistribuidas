package enums;

public enum TipoCategoria {

	Novato,
	Calificado,
	Experto,
	Master;

	private String tipoCat;

	private TipoCategoria(){
		
	}
	
	private TipoCategoria(String tipo){
		this.tipoCat = tipo;
	}

	public String getTipoCategoria() {
		return tipoCat;
	}	
	
	public static TipoCategoria obtenerPorTipo(String tipoCat) {
		for (TipoCategoria e : values()) {
			if (e.tipoCat.equalsIgnoreCase(tipoCat))
				return e;
		}
		return null;
	}
}

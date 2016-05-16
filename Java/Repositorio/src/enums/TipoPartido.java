package enums;

public enum TipoPartido {

	LibreIndividual("Libre individual"),
	LibreParejas("Libre parejas"),
	Grupo("Cerrada"); // 'cerrada' en el uml

	
	private String tipoPartido;

	
	
	TipoPartido(String tipo){
		this.tipoPartido = tipo;
	}

	public String getTipoPartido() {
		return tipoPartido;
	}	
	
	public static TipoPartido obtenerPorTipo(String tipoPartido) {
		for (TipoPartido e : values()) {
			if (e.tipoPartido.equalsIgnoreCase(tipoPartido))
				return e;
		}
		return null;
	}
}

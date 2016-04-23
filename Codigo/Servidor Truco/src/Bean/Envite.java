package Bean;

public class Envite extends Movimiento {
	private TipoEnvite tipoEnvite; ///////VER COMO CODIFICAR ENUMERATION

	public TipoEnvite getTipoEnvite() {
		return tipoEnvite;
	}

	public void setTipoEnvite(TipoEnvite tipoEnvite) {
		this.tipoEnvite = tipoEnvite;
	}

	public Envite(TipoEnvite tipoEnvite) {
		this.tipoEnvite = tipoEnvite;
	}

	public Envite() {
	}
	
	
}

package Bean;

//falta lo de persistencia

public class Envite extends Movimiento {

	private TipoEnvite tipoEnvite; 

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

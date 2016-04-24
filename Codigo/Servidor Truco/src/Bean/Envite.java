package Bean;

import javax.persistence.*;

@DiscriminatorValue("env")
public class Envite extends Movimiento {

	@Column (columnDefinition = "int")
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

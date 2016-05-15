package bean;

import javax.persistence.*;

import enums.TipoEnvite;


@Entity
@DiscriminatorValue("env")
public class Envite extends Movimiento {

	@Column (name = "tipo_envite", columnDefinition = "tinyint")
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

package dtos;

import java.io.Serializable;

import enums.TipoEnvite;

public class EnviteDTO extends MovimientoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private TipoEnvite tipoEnvite;

	public EnviteDTO() {

		super();
	}

	public TipoEnvite getTipoEnvite() {
		return tipoEnvite;
	}

	public void setTipoEnvite(TipoEnvite tipoEnvite) {
		this.tipoEnvite = tipoEnvite;
	}

}

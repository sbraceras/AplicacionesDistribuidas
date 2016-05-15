package dtos;

import enums.TipoEnvite;

public class EnviteDTO extends MovimientoDTO{
	
	
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

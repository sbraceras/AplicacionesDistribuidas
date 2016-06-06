package bean;

import javax.persistence.*;

import enums.TipoEnvite;


@Entity
@DiscriminatorValue("env")
public class Envite extends Movimiento {

	@Column (name = "tipo_envite", columnDefinition = "tinyint")
	private TipoEnvite tipoEnvite;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_jugador")
	private Jugador jugador;

	public Envite() {
	}

	public TipoEnvite getTipoEnvite() {
		return tipoEnvite;
	}

	public void setTipoEnvite(TipoEnvite tipoEnvite) {
		this.tipoEnvite = tipoEnvite;
	}

	public Envite(TipoEnvite tipoEnvite) {
		this.tipoEnvite = tipoEnvite;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public boolean sosAlgunEnvido() {
		return	(this.tipoEnvite.equals(TipoEnvite.Envido))			||
				(this.tipoEnvite.equals(TipoEnvite.EnvidoEnvido))	||
				(this.tipoEnvite.equals(TipoEnvite.RealEnvido))		||
				(this.tipoEnvite.equals(TipoEnvite.FaltaEnvido));
	}

	public boolean sosAlgunTruco() {
		return	(this.tipoEnvite.equals(TipoEnvite.Truco))		||
				(this.tipoEnvite.equals(TipoEnvite.ReTruco))	||
				(this.tipoEnvite.equals(TipoEnvite.ValeCuatro));
	}

}

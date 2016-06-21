package interfaz;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import dtos.CartaJugadorDTO;
import dtos.JugadorDTO;
import dtos.MovimientoDTO;
import dtos.PartidoDTO;
import dtos.PuntajeParejaDTO;
import enums.TipoEnvite;


public interface TDATruco extends Remote {

	public JugadorDTO login(String apodo, String contrasena) throws RemoteException;

	public PartidoDTO jugarLibreIndividual(JugadorDTO jugador) throws RemoteException;

	public List<CartaJugadorDTO> obtenerCartasJugador (PartidoDTO partido, JugadorDTO jugador) throws RemoteException;

	public List<PuntajeParejaDTO> obtenerPuntajeChico (PartidoDTO partido, JugadorDTO jugador) throws RemoteException;

	public List<MovimientoDTO> obtenerMovimientosUltimaBaza (PartidoDTO partido, JugadorDTO jugador) throws RemoteException;

	public List<TipoEnvite> obtenerEnvitesDisponibles(PartidoDTO partido, JugadorDTO jugador) throws RemoteException;

	public List<PartidoDTO> tengoPartido(JugadorDTO jugador) throws RemoteException;

	public JugadorDTO obtenerJugadorActual(PartidoDTO partido, JugadorDTO jugador) throws RemoteException;

	public void nuevoMovimientoPartido(PartidoDTO partido, JugadorDTO turnoJugador, MovimientoDTO movimiento) throws RemoteException;

}

package interfaz;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import dtos.CartaJugadorDTO;
import dtos.GrupoDTO;
import dtos.JugadorDTO;
import dtos.ManoDTO;
import dtos.MovimientoDTO;
import dtos.ParejaDTO;
import dtos.PartidoDTO;
import dtos.PuntajeParejaDTO;
import enums.TipoEnvite;
import enums.TipoPartido;
import exceptions.ControladorException;
import exceptions.PartidoException;


public interface TDATruco extends Remote {

	public PartidoDTO jugarLibreIndividual(JugadorDTO jugador) throws RemoteException;

	public List<CartaJugadorDTO> obtenerCartasJugador (PartidoDTO partido, JugadorDTO jugador) throws RemoteException;

	public List<PuntajeParejaDTO> obtenerPuntajeChico (PartidoDTO partido, JugadorDTO jugador) throws RemoteException;

	public List<MovimientoDTO> obtenerMovimientosUltimaBaza (PartidoDTO partido, JugadorDTO jugador) throws RemoteException;

	public List<TipoEnvite> obtenerEnvitesDisponibles(PartidoDTO partido, JugadorDTO jugador) throws RemoteException;

	public List<PartidoDTO> tengoPartido(JugadorDTO jugador) throws RemoteException;

	public JugadorDTO obtenerJugadorActual(PartidoDTO partido, JugadorDTO jugador) throws RemoteException;

	public void nuevoMovimientoPartido(PartidoDTO partido, JugadorDTO turnoJugador, MovimientoDTO movimiento) throws RemoteException;

	public JugadorDTO login(JugadorDTO jg) throws RemoteException;

	public void registrarJugador(JugadorDTO jg) throws RemoteException;
	
	public void crearGrupo(GrupoDTO dto, JugadorDTO administrador) throws RemoteException;
	
	public PartidoDTO obtenerUltimoPartidoPendienteModalidad (TipoPartido tipoPartido, JugadorDTO jugadorDTO) throws RemoteException;

	public List<ParejaDTO> obtenerParejasPartido(PartidoDTO partido) throws RemoteException;

	public boolean partidoEstaTerminado (PartidoDTO partido, JugadorDTO jugador) throws RemoteException;
	
	public List<JugadorDTO> obtenerGanadoresBazas (PartidoDTO partido, JugadorDTO jugador) throws RemoteException;

	public ManoDTO obtenerUltimaManoActiva (PartidoDTO partido, JugadorDTO jugador) throws RemoteException;

	public void cerrarSesion(JugadorDTO jg) throws RemoteException;

}

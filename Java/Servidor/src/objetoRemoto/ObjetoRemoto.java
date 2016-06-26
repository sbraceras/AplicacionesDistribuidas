package objetoRemoto;

import interfaz.TDATruco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import controlador.ServicioCentral;
import dtos.CartaDTO;
import dtos.CartaJugadorDTO;
import dtos.CartaTiradaDTO;
import dtos.JugadorDTO;
import dtos.MovimientoDTO;
import dtos.PartidoDTO;
import dtos.PuntajeParejaDTO;
import enums.TipoEnvite;
import exceptions.BazaException;
import exceptions.ControladorException;
import exceptions.JugadorException;
import exceptions.PartidoException;

public class ObjetoRemoto extends UnicastRemoteObject implements TDATruco {

	private static final long serialVersionUID = 1L;
	private ServicioCentral controlador;

	public ObjetoRemoto() throws RemoteException {
		super();

		controlador = ServicioCentral.getInstance();
	}

	public JugadorDTO login(JugadorDTO jg) throws JugadorException {
		//Intengo hacer login//
		try {
			return controlador.iniciarSesion(jg);
		} catch (JugadorException e) {
			throw new JugadorException(e.getMessage());
		}
	}
	
	@Override
	public void registrarJugador(JugadorDTO jg) throws RemoteException {
		// TODO Auto-generated method stub
		controlador.registrarJugador(jg);
	}

	
	public List<CartaJugadorDTO> obtenerCartasJugador (PartidoDTO partido, JugadorDTO jugador) throws RemoteException {
		
		try {
			return controlador.obtenerCartasJugador(partido, jugador);
		} catch (ControladorException | PartidoException e) {
			throw new RemoteException(e.getMessage());
			
			/* DISCUTIR SI ESTA ES LA MEJOR MANERA DE RE-LANZAR LA EXCEPTION */
			
		}
	}
	
	public List<PuntajeParejaDTO> obtenerPuntajeChico (PartidoDTO partido, JugadorDTO jugador) throws RemoteException {
		
		try {
			return controlador.obtenerPuntajeChico(partido, jugador);
		} catch (ControladorException | PartidoException e) {
			throw new RemoteException(e.getMessage());
			
			/* DISCUTIR SI ESTA ES LA MEJOR MANERA DE RE-LANZAR LA EXCEPTION */
			
		}
	}

	public List<MovimientoDTO> obtenerMovimientosUltimaBaza(PartidoDTO partido, JugadorDTO jugador) throws RemoteException {
		
		try {
			return controlador.obtenerMovimientosUltimaBaza(partido, jugador);
		}
		catch (ControladorException | PartidoException e) {
			throw new RemoteException(e.getMessage());
			
			/* DISCUTIR SI ESTA ES LA MEJOR MANERA DE RE-LANZAR LA EXCEPTION */
		}
	}

	public List<TipoEnvite> obtenerEnvitesDisponibles(PartidoDTO partido, JugadorDTO jugador) throws RemoteException {
	
		try {
			return controlador.obtenerEnvitesDisponibles(partido, jugador);
		}
		catch (PartidoException | ControladorException e) {
			throw new RemoteException(e.getMessage());
			
			/* DISCUTIR SI ESTA ES LA MEJOR MANERA DE RE-LANZAR LA EXCEPTION */
		}
	}
	
	public PartidoDTO jugarLibreIndividual(JugadorDTO jugador) {
		
		return controlador.jugarLibreIndividual(jugador);
	}
	
	public List<PartidoDTO> tengoPartido(JugadorDTO jugador) {
		
		return controlador.tengoPartido(jugador);
	}

	public JugadorDTO obtenerJugadorActual(PartidoDTO part, JugadorDTO jugador) throws RemoteException {
		
		try {
			return controlador.obtenerJugadorActual(part, jugador);
		} catch (ControladorException | PartidoException e) {
			
			throw new RemoteException(e.getMessage());
			
			/* DISCUTIR SI ESTA ES LA MEJOR MANERA DE RE-LANZAR LA EXCEPTION */
		}
		
	}

	public void nuevoMovimientoPartido(PartidoDTO partido, JugadorDTO turnoJugador, MovimientoDTO movimiento) throws RemoteException {
	
		try {
			controlador.nuevoMovimientoPartido(partido, turnoJugador, movimiento);
		} catch (ControladorException | PartidoException | BazaException | JugadorException e) {
			throw new RemoteException(e.getMessage());
			
			/* DISCUTIR SI ESTA ES LA MEJOR MANERA DE RE-LANZAR LA EXCEPTION */
		}
	}

}
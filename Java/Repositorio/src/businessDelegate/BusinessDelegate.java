package businessDelegate;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import dtos.CartaJugadorDTO;
import dtos.GrupoDTO;
import dtos.JugadorDTO;
import dtos.MovimientoDTO;
import dtos.PartidoDTO;
import dtos.PuntajeParejaDTO;
import enums.TipoEnvite;
import enums.TipoPartido;
import interfaz.TDATruco;

public class BusinessDelegate {

	public TDATruco objetoRemoto;

	public BusinessDelegate() throws RemoteException {
		
		
		/************************************************************************************************************/
		/************************************************************************************************************/
		/************************************************************************************************************/

		/**********************     VERIFICAR EL TEMA DE 'throws RemoteException'            ************************/
		/**********************		EL BusinessDelegate, DEBE LANZAR 'RemoteException'       ************************/
		/************************************************************************************************************/

		/************************************************************************************************************/
		/************************************************************************************************************/
		/************************************************************************************************************/

		super();

		try {
			objetoRemoto = (TDATruco) Naming.lookup("//localhost/ServicioCentral");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			throw new RemoteException("No pudo encontrarse el Servicio Central. " + e.getMessage());
		}
	}

	public JugadorDTO login(JugadorDTO jg) throws RemoteException {
		return objetoRemoto.login(jg);
	}
	
	public List<CartaJugadorDTO> obtenerCartasJugador (PartidoDTO partido, JugadorDTO jugador) throws RemoteException {
		try {
			return objetoRemoto.obtenerCartasJugador(partido, jugador);
		} catch (RemoteException e) {

			throw new RemoteException("Se produjo un error al intentar obtener las cartas: " + e.getMessage());
		}
	}
	
	public List<MovimientoDTO> obtenerMovimientosUltimaBaza (PartidoDTO partido, JugadorDTO jugador) throws RemoteException {
		try {
			return objetoRemoto.obtenerMovimientosUltimaBaza(partido, jugador);
		} catch (RemoteException e) {
			
			throw new RemoteException("Se produjo un error al intentar obtener las Ultimos Movimientos: " + e.getMessage());
		}
	}	
	
	public List<TipoEnvite> obtenerEnvitesDisponibles(PartidoDTO partido, JugadorDTO jugador) throws RemoteException {
		try {
			return objetoRemoto.obtenerEnvitesDisponibles(partido, jugador);
		} catch (RemoteException e) {
			
			throw new RemoteException("Se produjo un error al intentar obtener los Envites Disponibles: " + e.getMessage());
		}
	}	
	
	public PartidoDTO jugarLibreIndividual(JugadorDTO jugador) throws RemoteException {
		return objetoRemoto.jugarLibreIndividual(jugador);
	}

	public List<PartidoDTO> tengoPartido(JugadorDTO jugador) throws RemoteException {
		return objetoRemoto.tengoPartido(jugador);
	}

	public JugadorDTO obtenerJugadorActual(PartidoDTO part, JugadorDTO jugador) throws RemoteException {
		try {
			return objetoRemoto.obtenerJugadorActual(part,jugador);
		} catch (RemoteException e) {
			
			throw new RemoteException("Se produjo un error al obtener el Jugador Actual: " + e.getMessage());
		}
	}

	public void nuevoMovimientoPartido(PartidoDTO partido, JugadorDTO turnoJugador, MovimientoDTO movimiento) throws RemoteException {
		try {
			objetoRemoto.nuevoMovimientoPartido(partido, turnoJugador, movimiento);
		} catch (RemoteException e) {
			
			throw new RemoteException("Se produjo un error al hacer el nuevo Movimiento: " + e.getMessage());
		}
	}
	
	public List<PuntajeParejaDTO> obtenerPuntajeChico (PartidoDTO partido, JugadorDTO jugador) throws RemoteException {
		try {
			return objetoRemoto.obtenerPuntajeChico(partido, jugador);
		} catch (RemoteException e) {
			throw new RemoteException("Se produjo un error al obtener el Puntaje del Chico: " + e.getMessage());
		}
	}

	public void registrarJugador(JugadorDTO jg) throws RemoteException {
		try {
			objetoRemoto.registrarJugador(jg);
		} catch (RemoteException e) {
			throw new RemoteException("Se produjo un error al querer registrar el jugador.");
		}	
	}
	
	public void crearGrupo(GrupoDTO dto, JugadorDTO administrador) throws RemoteException {
		
		try {
			objetoRemoto.crearGrupo(dto, administrador);
		} catch (RemoteException e) {
			throw new RemoteException("Se produjo un error al querer crear el grupo. " + e.getMessage());
		}	
	}
	
	public PartidoDTO obtenerUltimoPartidoPendienteModalidad (TipoPartido tipoPartido, JugadorDTO jugadorDTO) throws RemoteException {
		
		try {
			return objetoRemoto.obtenerUltimoPartidoPendienteModalidad(tipoPartido, jugadorDTO);
		} catch (RemoteException e) {
			throw new RemoteException("Se produjo un error al verificar partidos pendientes. " + e.getMessage());
		}
	}


	
}

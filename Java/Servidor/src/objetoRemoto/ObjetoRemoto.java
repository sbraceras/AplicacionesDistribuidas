package objetoRemoto;

import interfaz.TDATruco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import controlador.ServicioCentral;
import dtos.CartaDTO;

public class ObjetoRemoto extends UnicastRemoteObject implements TDATruco {

	private static final long serialVersionUID = 1L;
	private ServicioCentral controlador;

	public ObjetoRemoto() throws RemoteException {
		controlador = ServicioCentral.getInstance();
	}

	@Override
	public CartaDTO ObtenerCarta(CartaDTO carta) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
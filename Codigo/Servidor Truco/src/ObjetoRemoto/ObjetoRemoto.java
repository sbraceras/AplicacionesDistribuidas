package ObjetoRemoto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Controlador.ServicioCentral;
import Interfaz.TDATruco;

public class ObjetoRemoto extends UnicastRemoteObject implements TDATruco {



	private static final long serialVersionUID = 1L;
	private ServicioCentral controlador;
	
	public ObjetoRemoto() throws RemoteException{
		
		controlador = ServicioCentral.getInstance();
		
	}
}
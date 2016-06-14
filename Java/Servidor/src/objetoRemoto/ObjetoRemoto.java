package objetoRemoto;

import interfaz.TDATruco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import controlador.ServicioCentral;
import dtos.CartaDTO;
import dtos.JugadorDTO;
import exceptions.JugadorException;

public class ObjetoRemoto extends UnicastRemoteObject implements TDATruco {

	private static final long serialVersionUID = 1L;
	private ServicioCentral controlador = ServicioCentral.getInstance();

	public ObjetoRemoto() throws RemoteException {
		super();
	}

	@Override
	public JugadorDTO login(String apodo, String password) throws JugadorException {
		// TODO Auto-generated method stub
		JugadorDTO jg = new JugadorDTO();
		jg.setApodo(apodo);
		jg.setPassword(password);
		
		//Intengo hacer login//
		return controlador.iniciarSesion(jg);
	}
	
	
	@Override
	public CartaDTO ObtenerCarta(CartaDTO carta) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
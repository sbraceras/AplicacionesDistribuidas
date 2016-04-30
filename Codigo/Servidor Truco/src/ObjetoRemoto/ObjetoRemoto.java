package ObjetoRemoto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Controlador.ServicioCentral;
import DAO.CartaDAO;
import DAO.HibernateDAO;
import DTO.CartaDTO;
import Interfaz.TDATruco;

public class ObjetoRemoto extends UnicastRemoteObject implements TDATruco {



	private static final long serialVersionUID = 1L;
	private ServicioCentral controlador;
	
	public ObjetoRemoto() throws RemoteException{
		
		controlador = ServicioCentral.getInstance();
		
	}


	/*Funcion de Prueba ELIMINAR */
	
	public CartaDTO ObtenerCarta(CartaDTO carta) {
		
		CartaDTO c = CartaDAO.getInstancia().ObtenerCarta(carta).toDTO();
		
		System.out.print("id_carta" + c.getId() + "palo" + c.getPalo());
		
		return c;
	}
	
}
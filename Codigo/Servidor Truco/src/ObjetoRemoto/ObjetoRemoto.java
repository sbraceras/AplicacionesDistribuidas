package ObjetoRemoto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Controlador.ServicioCentral;
import DAO.HibernateDAO;
import DTO.CartaDTO;
import Interfaz.TDATruco;

public class ObjetoRemoto extends UnicastRemoteObject implements TDATruco {



	private static final long serialVersionUID = 1L;
	private ServicioCentral controlador;
	
	public ObjetoRemoto() throws RemoteException{
		
		controlador = ServicioCentral.getInstance();
		
	}


	public CartaDTO ObtenerCarta(CartaDTO carta) {
		CartaDTO c = new CartaDTO();
		c = HibernateDAO.getInstancia().ObtenerCarta(carta);
		System.out.print("id_carta" + c.getId() + "palo" + c.getPalo());
		
		return DAO.HibernateDAO.getInstancia().ObtenerCarta(carta);
	}
	
}
package interfaz;

import java.rmi.Remote;


import java.rmi.RemoteException;

import dtos.CartaDTO;


public interface TDATruco extends Remote {
	
	public CartaDTO ObtenerCarta(CartaDTO carta) throws RemoteException;
	
}

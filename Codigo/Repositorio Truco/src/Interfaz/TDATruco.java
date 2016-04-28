package Interfaz;

import java.rmi.Remote;


import java.rmi.RemoteException;

import DTO.CartaDTO;

public interface TDATruco extends Remote {
	
	public CartaDTO ObtenerCarta(CartaDTO carta) throws RemoteException;
	
}

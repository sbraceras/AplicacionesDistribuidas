package interfaz;

import java.rmi.Remote;


import java.rmi.RemoteException;

import dtos.CartaDTO;
import dtos.JugadorDTO;
import exceptions.JugadorException;


public interface TDATruco extends Remote {
	
	public CartaDTO ObtenerCarta(CartaDTO carta) throws RemoteException;

	public JugadorDTO login(String apodo, String contrasena) throws JugadorException;
	
}

package businessDelegate;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import dtos.JugadorDTO;
import exceptions.JugadorException;
import interfaz.TDATruco;

public class BusinessDelegate {

	public TDATruco objetoRemoto;
	
	public BusinessDelegate() throws RemoteException{
		super();
		try {
			objetoRemoto = (TDATruco) Naming.lookup("//localhost/ServicioCentral");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			//Generar exception real
			throw new RemoteException("Se produjeron problemas al intentar encontrar el servicio central");
		}
	}	
																										
	public JugadorDTO login(String apodo, String contrasena) throws JugadorException, RemoteException {
		return objetoRemoto.login(apodo, contrasena);
	}																								//sessionService = (SesionService) Naming.lookup("//" + webServerProperties.getProperty("server.url") + "/" + SesionService.SERVICENAME);
		
	

}

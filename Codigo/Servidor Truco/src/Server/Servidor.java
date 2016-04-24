package Server;


import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import Hibernate.HibernateUtil;
import Interfaz.TDATruco;
import ObjetoRemoto.ObjetoRemoto;

public class Servidor {
	
     TDATruco objetoRemoto;
	
     public static void main (String [] args)
     {
    	 new Servidor();
     }
     
     public Servidor(){
    	 iniciar();
     }

     
     public void iniciar(){
    	 try{
    		  
    		 
    		 LocateRegistry.createRegistry(1099);
    		 
    		 objetoRemoto = new ObjetoRemoto();
    		 
    		 Naming.rebind ("//localhost/ObjetoRemoto", objetoRemoto);
    		 
    		 System.out.println("Fijado en //localhost/ServicioCentral");
    		 
    		 new HibernateUtil(); //crea la conexión a la BD
    		 
    		 /*ServicioCentral.getModelo().registrarObserver((Observer) objetoRemoto);*/
    	 }
    	 
    	 catch (Exception e)
    	 {
    		 e.printStackTrace();
    	 }
    		 
    	 
     }
}

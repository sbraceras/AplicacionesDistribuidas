package DAO;

import org.hibernate.*;

import Bean.Jugador;
import DTO.JugadorDTO;
import Hibernate.HibernateUtil;

public class JugadorDAO {
	
	protected static SessionFactory sf =null;
	protected static JugadorDAO instancia;
	protected Session s =null;

	
	public static JugadorDAO getinstance(){
		
		if(instancia==null){
			
			sf =HibernateUtil.getSessionFactory();
			instancia = new JugadorDAO();
		}
		return instancia;
	}
	
	public Session getSession(){
		
		if(s==null || !s.isOpen())
		{
			s= sf.openSession();
		}
		return s;
		
	}
	
	public void closeSession(){
		if ( s.isOpen()) s.close();
	}
	
	public void guardarJugador (Jugador jugador){
		
		Transaction t =null;
		s= this.getSession();
		
		try{
			
			t= s.beginTransaction();
			s.save(jugador);
			System.out.println("Jugador Guardado");
			t.commit();
			
		}
		catch(Exception e){
			System.out.println("Error al Guardar Jugador");
		}
	}
	
	public Jugador buscarJugador (JugadorDTO jugador){
		
		Session s = this.getSession();
		Jugador devolver;
		try{
			System.out.println("Me meti aca");
			devolver = (Jugador) s.createQuery("select j from Jugador j inner join j.ranking where j.id =:id").setParameter("id", jugador.getId()).uniqueResult();
			System.out.println(devolver.getApodo());
			return devolver;
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error al buscar jugador");
			return null;
		}
	}
}

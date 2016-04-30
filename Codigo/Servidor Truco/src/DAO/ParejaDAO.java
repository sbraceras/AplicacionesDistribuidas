package DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Bean.Pareja;
import DTO.ParejaDTO;
import Hibernate.HibernateUtil;

public class ParejaDAO {
	
	protected static SessionFactory sf =null;
	protected static ParejaDAO instancia;
	protected Session s = null;
	
	
	public ParejaDAO getinstance (){
		
		if(instancia ==null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new ParejaDAO();
		}
		return instancia;
	}
	
	
	public Session getSession (){
		
		if(s ==null || !s.isOpen())
			s= sf.openSession();
		return s;
	}
	
	public void guardarPareja (Pareja pareja){
		
		Transaction t = null;
		Session s = this.getSession();
		try{
			
			t= s.beginTransaction();
			s.save(pareja);
			System.out.println("Pareja Guardada");
			s.flush();
			t.commit();
			s.close();
		}
		catch(Exception e){
			System.out.println("Error al guardar Parejas");
			e.printStackTrace();
		}
		
	}
	
	public Pareja buscarPareja (ParejaDTO pareja){
		
		Session s = this.getSession();
		Pareja devolver;
		try{
			
			devolver = (Pareja) s.createQuery("select p from Pareja p inner join p.jugador1 p1 inner join p.jugador2 p2 where p.id=:id").setParameter("id",pareja.getId()).uniqueResult();
			return devolver;
		}
		catch(Exception e)
		{
			System.out.println("Error al obtener Pareja");
			e.printStackTrace();
			return null;
		}
	}

}

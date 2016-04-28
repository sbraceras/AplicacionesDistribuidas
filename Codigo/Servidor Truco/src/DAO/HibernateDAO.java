package DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Bean.Carta;
import DTO.CartaDTO;
import Hibernate.HibernateUtil;

public class HibernateDAO {

	protected static HibernateDAO instancia;
	protected static SessionFactory sf = null;
	protected Session session = null;
	
	public static HibernateDAO getInstancia(){
		if (instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new HibernateDAO();		
		}
		return instancia;
	}
	
	public Session getSession(){
		if (session ==null || !session.isOpen()){
			session = sf.openSession();
		}
		return session;
	}
	
	public void closeSession(){
		if ( session.isOpen()) session.close();
	}
	

}

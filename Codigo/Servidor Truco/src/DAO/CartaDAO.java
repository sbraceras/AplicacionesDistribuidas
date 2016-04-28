package DAO;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Bean.Carta;
import DTO.CartaDTO;
import ENUMS.Palo;
import Hibernate.HibernateUtil;

public class CartaDAO {

	protected static CartaDAO instancia;
	protected static SessionFactory sf = null;
	protected Session session = null;
	
	public static CartaDAO getInstancia(){
		if (instancia == null){
			sf = HibernateUtil.getSessionFactory();
			instancia = new CartaDAO();		
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
	
	public Carta ObtenerCarta(CartaDTO carta){
		Session s = this.getSession();
		Carta devolver = new Carta();
		try{
			devolver =  (Carta) s.createQuery("select c from Carta c where c.id =:idcarta").setParameter("idcarta", carta.getId()).uniqueResult();
			return devolver;
			
		}catch(Exception e){
			System.out.println("Error al obtener carta");
			return devolver;
		}
		
	}
	
	public void guardarCarta (Carta c){
		
		Transaction t = null;
		Session s = this.getSession();
		try{
			
			t= s.beginTransaction();
			s.save(c);
			System.out.println("Carta Guardada");
			t.commit();
			
		}
		catch(Exception e){
			System.out.println("ERROR AL GUARDAR CARTA");
		}
	}
}
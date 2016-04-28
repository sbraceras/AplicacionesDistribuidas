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
	
	public CartaDTO ObtenerCarta(CartaDTO carta){
		Session s = this.getSession();
		Carta c= new Carta();
		CartaDTO devolver = new CartaDTO();
		try{
			c = (Carta) s.createQuery("select * from Carta c where c.id =:idcarta").setParameter("idcarta", carta.getId()).uniqueResult();
			devolver.setId(c.getId());
			devolver.setNumero(c.getNumero());
			devolver.setPalo(c.getPalo());
			devolver.setPosicionValor(c.getPosicionValor());
			return devolver;
			
		}catch(Exception e){
			System.out.print("Error al obtener carta");
			return devolver;
		}
		
	}
}

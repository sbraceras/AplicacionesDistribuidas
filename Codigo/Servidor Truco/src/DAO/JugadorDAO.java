package DAO;

import java.util.ArrayList;


import org.hibernate.*;

import Bean.Grupo;
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
			s.flush();
			t.commit();
			s.close();
			
		}
		catch(Exception e){
			System.out.println("Error al Guardar Jugador");
		}
	}
	
	public Jugador buscarJugador (JugadorDTO jugador){
		
		Session s = this.getSession();
		Jugador devolver;
		try{
			devolver = (Jugador) s.createQuery("select j from Jugador j inner join j.ranking where j.id =:id").setParameter("id", jugador.getId()).uniqueResult();
			s.close();
			return devolver;
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error al buscar jugador");
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Grupo> obtenerGruposJugador (Jugador jugador){
		
		Session s = this.getSession();
		ArrayList<Grupo> devolver;
		try{
			
			devolver = (ArrayList<Grupo>) s.createQuery("select g from Jugador j inner join j.grupos g where j.id =:id").setParameter("id", jugador.getId()).list();
			s.close();
			return devolver;
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error al buscar jugador");
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Jugador> obtenerJugadores(){
		
		Session s = this.getSession();
		ArrayList<Jugador> jugadores;
		try{
			
			jugadores = (ArrayList<Jugador>) s.createQuery("Select j from Jugador j inner join j.grupos").list();
			s.close();
			return jugadores;
		}
		catch(Exception e){
			
			System.out.println("Error al obtener todos los Jugadores");
			e.printStackTrace();
			return null;
		}
	}
}

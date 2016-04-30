package Controlador;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Bean.*;
import DAO.CartaDAO;
import DAO.GrupoDAO;
import DAO.HibernateDAO;
import DAO.JugadorDAO;
import DTO.*;
import ENUMS.Palo;
import ENUMS.TipoCategoria;
import ENUMS.TipoPartido;

/**
 * Es el controlador del negocio
**/
public class ServicioCentral {
	private static ServicioCentral instancia;
	private ArrayList<Jugador> jugadores;
	private ArrayList<Partido> partidos;
	private ArrayList<Grupo> grupos;
	private ArrayList<Jugador> sesiones;
	private ArrayList<Jugador> esperandoLibreInvidividual;
	private ArrayList<Pareja> esperandoLibreParejas;
	private static ServicioCentral controlador;
	
	
	
	public ServicioCentral() {
		this.jugadores = new ArrayList<Jugador>();
		this.partidos = new ArrayList<Partido>();
		this.grupos = new ArrayList<Grupo>();
		this.sesiones = new ArrayList<Jugador>();
		this.esperandoLibreInvidividual = new ArrayList<Jugador>();
		this.esperandoLibreParejas = new ArrayList<Pareja>();
		
		/*Jugador jug = new Jugador("Gaston","Gaston", "123");
		
		JugadorDAO.getinstance().guardarJugador(jug);
		
		jug = null;
		
		JugadorDTO pepe = new JugadorDTO();
		pepe.setId(1);

		jug = JugadorDAO.getinstance().buscarJugador(pepe);
		
		Grupo juan = new Grupo("los totora", jug);
		
		GrupoDAO.getInstancia().guardarGrupo(juan);
		juan=null;
		GrupoDTO grupo = new GrupoDTO();
		grupo.setId(1);
		juan = GrupoDAO.getInstancia().buscarGrupo(grupo);
		
		System.out.println(juan.getNombre());*/
		
		
				
	}

	public static ServicioCentral getInstance() {
		
		if(controlador==null)
		{
			controlador = new ServicioCentral();
		}
		return controlador;
	}
	

	

	
	public void registrarJugador(JugadorDTO jugador) {
		Jugador validar = obtenerJugador(jugador);
		if(validar == null){
		    Jugador jug= new Jugador(jugador.getApodo(), jugador.getPassword(), jugador.getPassword());
		    JugadorDAO.getinstance().guardarJugador(jug);
			jugadores.add(jug);
			
		}
		
	}
	
	private Jugador obtenerJugador(JugadorDTO jugador) {
		
		for(int i =0; i<jugadores.size();i++){
			if(jugadores.get(i).getId() == jugador.getId())
				return jugadores.get(i);
		}
		
		/* Todavia no lo encontro lo busco en memoria */
		
		Jugador devolver = JugadorDAO.getinstance().buscarJugador(jugador);
		
		if(devolver !=null)
			jugadores.add(devolver); // lo agrego a memoria
		
		return devolver;
	}
	
	public boolean existeGrupo(GrupoDTO dto) {
		
		if(obtenerGrupo(dto)!=null)
			return true;
		return false;
	}
	
	
	public RankingDTO obtenerRankingGeneral(JugadorDTO jugador, ArrayList<RankingDTO> rank){
		Jugador jug= obtenerJugador(jugador);
		if (jug !=null){
			RankingDTO devolver = new RankingDTO();
			devolver = obtenerRanking(jug);
			return devolver;
		}
		return null;
	}
	
	public RankingDTO obtenerRanking(Jugador jug){
				
		return jug.getRanking().toDTO();
	}
	

	

	
	public void crearGrupo(GrupoDTO dto, JugadorDTO administrador) {
		
		if(!existeGrupo(dto))
		{
			Jugador jug = obtenerJugador(administrador);
			if(jug!=null)
			{
				Grupo grupo = new Grupo(dto.getNombre(), jug);
				GrupoDAO.getInstancia().guardarGrupo(grupo);
				grupos.add(grupo);
			}
		}
		
	}
	

	public void agregarJugadorGrupo(ArrayList<JugadorDTO> agregar, GrupoDTO dto, JugadorDTO administrador) {
	
		Grupo grupo = obtenerGrupo(dto);
		
		if(grupo !=null){
			
			Jugador jugador = obtenerJugador(administrador);
			
			if(jugador !=null)
			{
				if(grupo.esAdministrador(jugador)){
					
					Jugador jug2;
					
					for(int i=0; i<agregar.size(); i++){
						
							jug2= obtenerJugador(agregar.get(i));
							if(jug2!=null){
								grupo.agregarMiembro(jug2);
							}
						
					}
					
				}
			}
			
		}
		
	}

	
	/* HACER SEGUN DIAGRAMA DE SECUENCIAS */

	public void armarParejaGrupo(ArrayList<JugadorDTO> integrantes, GrupoDTO dto, JugadorDTO administrador) {
	
		
		Grupo grupo = obtenerGrupo(dto);
		
		if(grupo!= null){
			
			Jugador jugador = obtenerJugador(administrador);
			
			if(jugador!=null){
				
				if(grupo.esAdministrador(jugador)){
					
				
						ArrayList<Jugador> parejas = new ArrayList<Jugador>();
						
						for(int i=0; i<integrantes.size(); i++){
							
							parejas.add(obtenerJugador(integrantes.get(i)));							
						}
						
						grupo.armarPareja(parejas);
					
					
				}
				
				
			}
			
			
		}
		
		
	}
	
	
	/* HACER SEGUN DIAGRAMA DE SECUENCIAS */
	public void crearPartidaGrupo(ArrayList<ParejaDTO> parejas, GrupoDTO dto, JugadorDTO administrador) {
	
		
		Grupo grupo = obtenerGrupo(dto);
		
		if(grupo!=null){
			
			Jugador jugador = obtenerJugador(administrador);
			
			if(jugador !=null){
				
				if(grupo.esAdministrador(jugador))
				{
					ArrayList<Pareja> ingresan = new ArrayList<Pareja>();
					
					for(int i=0; i< parejas.size(); i++){
						
						if(grupo.tenesPareja(parejas.get(i)))
							ingresan.add(grupo.obtenerPareja(parejas.get(i)));
					}
					
					if(ingresan.size()==2) /* tengo ambas parejas activas */
					{
						Date date = new Date();
						Partido partido = new Partido(ingresan, (Timestamp) date, TipoPartido.Grupo);
						grupo.agregarPartido(partido);
						partidos.add(partido);
					}
					
				}
				
			}
			
			
		}
		
	}
	
	/* HACER SEGUN DIAGRAMA DE SECUENCIAS */	
	public void jugarLibreIndividual(JugadorDTO jugador) {
		Jugador jug = obtenerJugador(jugador);
		if(jug!=null){
			esperandoLibreInvidividual.add(jug);
			if(esperandoLibreInvidividual.size() >= 4){
				armarParejasInvididual(jug.getCategoria());
				//seguir
			}
			
		}
	}
	
	/* HACER SEGUN DIAGRAMA DE SECUENCIAS */
	private ArrayList<Pareja> armarParejasInvididual(TipoCategoria categoria) {
	
		return null;
	}
	/* HACER SEGUN DIAGRAMA DE SECUENCIAS */
	private Partido armarPartido(ArrayList<Pareja> parejas, String tipoPartido) {
		
		return null;
	
	}
	/* HACER SEGUN DIAGRAMA DE SECUENCIAS */
	private Pareja obtenerParejaEnemiga(TipoCategoria categoria) {
	
		return null;
	}
	/* HACER SEGUN DIAGRAMA DE SECUENCIAS */
	public void jugarLibreParejas(ParejaDTO pareja) {
	
	}
	
	public void eliminarMiembroGrupo(JugadorDTO jugador, GrupoDTO grupo, JugadorDTO administrador) {
	
		Jugador aux = obtenerJugador(jugador);
		Grupo grup;
		Jugador administradorReal;
		if(aux!=null)
		{
			grup = obtenerGrupo(grupo);
			if(grup!=null)
			{
				administradorReal = obtenerJugador(administrador);
				if(grup.esAdministrador(administradorReal))
				{
					grup.eliminarMiembroGrupo(aux);
				}
			}
		}
	}
	
	public void iniciarSesion(JugadorDTO jugador) {
	
		Jugador real = obtenerJugador(jugador);
		
		if(real != null)
		{
			if(real.getMail().equals(jugador.getMail()))
				if(real.getPassword().equals(jugador.getPassword()))
				{
					System.out.println("LogIn Correcto");
					sesiones.add(real);
				}
				else
					System.out.println("Contraseña Incorrecta");
			else
				System.out.println("Mail Incorrecto");
		}
		else
			System.out.println("El jugador no Existe");
	}
	
	
	public void cerrarSesion(JugadorDTO jugador) {
		
	
		for(int i=0; i<sesiones.size(); i++){
			
			if(sesiones.get(i).sosJugador(jugador))
				sesiones.remove(i);
		}
	
	}
	
	/* DESARROLLAR CON HQL */
	public ArrayList<JugadorDTO> obtenerJugadores() {
	
		ArrayList<Jugador> jug = JugadorDAO.getinstance().obtenerJugadores();
		
		ArrayList<JugadorDTO> devolver = new ArrayList<JugadorDTO>();
		
		for(int i=0; i<jug.size(); i++){
			
			devolver.add(jug.get(i).toDTO());
		}
		return devolver;
	}
	
	/* DESARROLLAR */
	public ArrayList<PartidoDTO> obtenerPartidos(Timestamp fechaDesde, Timestamp fechaHasta, TipoPartido modalidad, JugadorDTO jugador) {
	
		return null;
	}
	
	public PartidoDTO obtenerPartidoJugado(PartidoDTO partido) {
	
		for(int i=0; i<partidos.size(); i++)
		{
			if(partido.getId()==partidos.get(i).getId())
				return partidos.get(i).toDTO();
		}
		
		/* faltar partido dao*/
		
		return null;
	}
	
	
	/* DESARROLLAR */
	public ChicoDTO obtenerChicoDTO(PartidoDTO partido, int chico) {
	
		return null;
	}
	/* DESARROLLAR */
	public ArrayList<RankingDTO> obtenerRankingGeneral(JugadorDTO jugador) {
	
			return null;
	}

	
	public ArrayList<RankingDTO> obtenerRankingGrupo(GrupoDTO grupo, JugadorDTO jugador) {
		Grupo grup = obtenerGrupo(grupo);
		if(grup!=null){

			return grup.obtenerRanking();
		}
		
		return null;

	}
	/* DESARROLLAR */
	public Pareja armarPareja(ArrayList<Jugador> jugadores) {
	
			return null;
	}
	
	
	public Grupo obtenerGrupo(GrupoDTO dto) {
			
			
			for(int i=0;i<grupos.size();i++){
				if(grupos.get(i).getNombre().equals(dto.getNombre()))
					return grupos.get(i);
			}
			
			/* No lo encontro en memoria */
			
			Grupo grupo = GrupoDAO.getInstancia().buscarGrupo(dto);
			
			if(grupo!=null)
			{
				grupos.add(grupo); /* lo agrego a memoria*/
			}
			
			return grupo;
	}
	public ArrayList<GrupoDTO> ObtenerGrupos (){


		List<Grupo> grup = GrupoDAO.getInstancia().obtenerGrupos();
		
		ArrayList<GrupoDTO> devolver = new ArrayList<GrupoDTO>();
		
		for(int i=0; i<grup.size(); i++){
			devolver.add(grup.get(i).toDto());
		}
		
		return devolver;
	}
	
}

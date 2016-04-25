package Controlador;

import java.sql.Timestamp;
import java.util.ArrayList;

import Bean.*;
import DTO.*;
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
	}

	public static ServicioCentral getInstance() {
		
		if(controlador==null)
		{
			controlador = new ServicioCentral();
		}
		return controlador;
	}
	
	
	/* HACER SEGUN DIAGRAMA DE SECUENCIA */
	
	public void registrarJugador(JugadorDTO jugador) {
	
	}
	
	private Jugador obtenerJugador(JugadorDTO jugador) {
		
		return null;	
	}
	
	public boolean existeGrupo(String nombre) {
		
		if(obtenerGrupo(nombre)!=null)
			return true;
		return false;
	}
	
	
	/* HACER SEGUN DIAGRAMA DE SECUENCIAS */
	
	public void crearGrupo(String nombre, JugadorDTO administrador) {
	
	}
	
	/* HACER SEGUN DIAGRAMA DE SECUENCIAS */
	
	public void agregarJugadorGrupo(ArrayList<JugadorDTO> jugadores, String grupo, JugadorDTO administrador) {
	
	}

	
	/* HACER SEGUN DIAGRAMA DE SECUENCIAS */
	public void armarParejaGrupo(ArrayList<JugadorDTO> integrantes, String grupo, JugadorDTO administrador) {
	
	}
	/* HACER SEGUN DIAGRAMA DE SECUENCIAS */
	public void crearPartidaGrupo(ArrayList<ParejaDTO> parejas, String grupo, JugadorDTO administrador) {
	
	}
	
	/* HACER SEGUN DIAGRAMA DE SECUENCIAS */	
	public void jugarLibreIndividual(JugadorDTO jugador) {
	
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
	
	public void eliminarMiembroGrupo(JugadorDTO jugador, String grupo, JugadorDTO administrador) {
	
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
	
		return null;
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
	/* DESARROLLAR */
	public ArrayList<RankingDTO> obtenerRankingGrupo(GrupoDTO grupo, JugadorDTO jugador) {
	
		return null;
	}
	/* DESARROLLAR */
	public Pareja armarPareja(ArrayList<Jugador> jugadores) {
	
			return null;
	}
	/* DESARROLLAR */
	public Grupo obtenerGrupo(String nombre) {
	
			return null;
	}
}

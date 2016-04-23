package Controlador;

import java.sql.Timestamp;
import java.util.ArrayList;

import Bean.*;
import DTO.*;

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
	
	
	public static ServicioCentral getInstance() {
	
	}
	
	public void registrarJugador(JugadorDTO jugador) {
	
	}
	
	private Jugador obtenerJugador(JugadorDTO jugador) {
		
		return null;	
	}
	
	public boolean existeGrupo(String nombre) {
		
		return false;
	
	}
	
	public void crearGrupo(String nombre, JugadorDTO administrador) {
	
	}
	
	public void agregarJugadorGrupo(ArrayList<JugadorDTO> jugadores, String grupo, JugadorDTO administrador) {
	
	}
	
	public void armarParejaGrupo(ArrayList<JugadorDTO> integrantes, String grupo, JugadorDTO administrador) {
	
	}
	
	public void crearPartidaGrupo(ArrayList<ParejaDTO> parejas, String grupo, JugadorDTO administrador) {
	
	}
	
	/*
	// que onda esto?
	public OpcionesDisponiblesDTO iniciarSesion(JugadorDTO jugador) {
	
		
	}*/
	
	public void jugarLibreIndividual(JugadorDTO jugador) {
	
	}
	
	private ArrayList<Pareja> armarParejasInvididual(TipoCategoria categoria) {
	
		return null;
	}
	
	private Partido armarPartido(ArrayList<Pareja> parejas, String tipoPartido) {
		
		return null;
	
	}
	
	private Pareja obtenerParejaEnemiga(TipoCategoria categoria) {
	
		return null;
	}
	
	public void jugarLibreParejas(ParejaDTO pareja) {
	
	}
	
	public void eliminarMiembroGrupo(JugadorDTO jugador, String grupo, JugadorDTO administrador) {
	
	}
	
	public void iniciarSesion(JugadorDTO jugador) {
	
	}
	
	public void cerrarSesion(JugadorDTO jugador) {
	
	}
	
	public ArrayList<JugadorDTO> obtenerJugadores() {
	
		return null;
	}
	
	public ArrayList<PartidoDTO> obtenerPartidos(Timestamp fechaDesde, Timestamp fechaHasta, TipoPartido modalidad, JugadorDTO jugador) {
	
		return null;
	}
	
	public PartidoDTO obtenerPartidoJugado(PartidoDTO partido) {
	
		return null;
	}
	
	public ChicoDTO obtenerChicoDTO(PartidoDTO partido, int chico) {
	
		return null;
	}
	
	public ArrayList<RankingDTO> obtenerRankingGeneral(JugadorDTO jugador) {
	
			return null;
	}
	
	public ArrayList<RankingDTO> obtenerRankingGrupo(GrupoDTO grupo, JugadorDTO jugador) {
	
		return null;
	}
	
	public Pareja armarPareja(ArrayList<Jugador> jugadores) {
	
			return null;
	}
	
	public Grupo obtenerGrupo(String nombre) {
	
			return null;
	}
}

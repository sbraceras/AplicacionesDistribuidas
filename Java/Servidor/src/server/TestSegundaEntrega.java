package server;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import controlador.ServicioCentral;
import dtos.*;
import enums.*;
import exceptions.*;

public class TestSegundaEntrega {

	public TestSegundaEntrega() {

		/*
		 * Jugador jug = new Jugador("Gaston","Gaston", "123");
		 * 
		 * JugadorDAO.getinstance().guardarJugador(jug);
		 * 
		 * jug = null;
		 * 
		 * JugadorDTO pepe = new JugadorDTO();
		 * pepe.setId(1);
		 * 
		 * jug = JugadorDAO.getinstance().buscarJugador(pepe);
		 * 
		 * Grupo juan = new Grupo("los totora", jug);
		 * 
		 * GrupoDAO.getInstancia().guardarGrupo(juan);
		 * juan=null;
		 * 
		 * GrupoDTO grupo = new GrupoDTO();
		 * grupo.setId(1);
		 * juan = GrupoDAO.getInstancia().buscarGrupo(grupo);
		 * 
		 * System.out.println(juan.getNombre());
		 */


		// ************** registrarJugador ************** //
/*
		JugadorDTO jugador = new JugadorDTO();

		jugador.setApodo("cgodio");
		jugador.setMail("cgodio@uade.edu.ar");
		jugador.setPassword("12345");

		try {
			ServicioCentral.getInstance().registrarJugador(jugador);
		} catch (JugadorException e) {
			System.err.println(e.getMessage());
		}

		jugador.setApodo("cgodio");
		jugador.setMail("cgodio@gmail.com");
		jugador.setPassword("99999");

		try {
			ServicioCentral.getInstance().registrarJugador(jugador);
		} catch (JugadorException e) {
			System.err.println(e.getMessage());
		}
*/

		// ************** iniciarSesion ************** //
/*
		jugador.setApodo("sbraceras");
		jugador.setPassword("TaTObrA");

		try {
			ServicioCentral.getInstance().iniciarSesion(jugador);
		} catch (JugadorException e) {
			System.err.println(e.getMessage());
		}
*/
		
		
		// ************** Crear Grupo ************** //
/*
		jugador.setId(4);
		
		GrupoDTO grupo = new GrupoDTO();
		grupo.setNombre("UADE");
				
		ServicioCentral.getInstance().crearGrupo(grupo, jugador);
*/
		
		// ************** Agregar Miembros a Grupos ************** //
/*
		ArrayList<JugadorDTO> jugadores = ServicioCentral.getInstance().obtenerJugadores();
		List<JugadorDTO> agregar = new ArrayList<JugadorDTO>();
		
		agregar.add(jugadores.get(1));
		agregar.add(jugadores.get(2));
		
		ServicioCentral.getInstance().agregarJugadorGrupo(agregar, grupo , jugador);
*/


		// ************** Jugar Libre Individual ************** //

		JugadorDTO jugador1 = new JugadorDTO();
		JugadorDTO jugador2 = new JugadorDTO();
		JugadorDTO jugador3 = new JugadorDTO();
		JugadorDTO jugador4 = new JugadorDTO();
		
		// hardcodeamos los id porque deberian venir desde la vista cargados
		
//		jugador1.setId(1);
		jugador1.setApodo("cgodio");
		jugador1.setPassword("12345");

//		jugador2.setId(4);
		jugador2.setApodo("sbraceras");
		jugador2.setPassword("TaTobrA");

//		jugador3.setId(3);
		jugador3.setApodo("gmasaro");	
		jugador3.setPassword("banfieldcapo");
		
//		jugador4.setId(15);
		jugador4.setApodo("ozamudio");
		jugador4.setPassword("yousomac");

		try {
			jugador1 = ServicioCentral.getInstance().iniciarSesion(jugador1);
		} catch (JugadorException e) {
			System.err.println(e.getMessage());
		}
		try {
			jugador2 = ServicioCentral.getInstance().iniciarSesion(jugador2);
		} catch (JugadorException e) {
			System.err.println(e.getMessage());
		}
		try {
			jugador3 = ServicioCentral.getInstance().iniciarSesion(jugador3);
		} catch (JugadorException e) {
			System.err.println(e.getMessage());
		}
		try {
			jugador4= ServicioCentral.getInstance().iniciarSesion(jugador4);
		} catch (JugadorException e) {
			System.err.println(e.getMessage());
		}	

		// ************** Controlamos si se armo Partido ************** //

		PartidoDTO part1 = ServicioCentral.getInstance().jugarLibreIndividual(jugador1);
		PartidoDTO part2 = ServicioCentral.getInstance().jugarLibreIndividual(jugador2);
		PartidoDTO part3 = ServicioCentral.getInstance().jugarLibreIndividual(jugador3);
		PartidoDTO part4 = ServicioCentral.getInstance().jugarLibreIndividual(jugador4);
				
		if (part4 != null)
			System.out.println("Se armo un partido! Inicio: " + part4.getFechaInicio());


		// ************** Verificamos a quien le toca jugar ************** //

		JugadorDTO turnoJugador = new JugadorDTO();
		try {
			turnoJugador = ServicioCentral.getInstance().obtenerJugadorActual(part4, jugador4);
			System.out.println("Le toca jugar a: " + turnoJugador.getApodo());
		} catch (ControladorException e) {
			e.printStackTrace();
		}


		// ************** Obtenemos los cantos Posibles ************** //

		try {
			List<TipoEnvite> opciones = ServicioCentral.getInstance().obtenerEnvitesDisponibles(part4, turnoJugador);
			if (opciones.isEmpty()) {
				System.out.println("No puede cantar nada");
			} else {
				for(TipoEnvite envite: opciones) {
					System.out.println("Puede cantar: " + envite.name());
				}
			}
		} catch (ControladorException e) {
			e.printStackTrace();
		}


		// ************** Obtenemos las cartas del Jugador ************** //

		List<CartaJugadorDTO> cartas = new ArrayList<CartaJugadorDTO>();
		try {
			cartas = ServicioCentral.getInstance().obtenerCartasJugador(part4, turnoJugador);
			for(CartaJugadorDTO cartaJugador: cartas) {
				System.out.println("Puede tirar: " + cartaJugador.getCarta().toString());
			}
		} catch (ControladorException e) {
			e.printStackTrace();
		}


		// ************** Tira su Primer Carta ************** //

		CartaTiradaDTO cartaTirada = new CartaTiradaDTO();
		cartaTirada.setFechaHora(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		cartaTirada.setCartaJugador(cartas.get(0));
		try {
			ServicioCentral.getInstance().nuevoMovimientoPartido(part4, turnoJugador, cartaTirada);
		} catch (ControladorException e) {
			e.printStackTrace();
		}
	}

}

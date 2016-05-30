package server;

import controlador.ServicioCentral;
import dtos.GrupoDTO;
import dtos.JugadorDTO;
import dtos.PartidoDTO;
import exceptions.JugadorException;

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

		// ************** iniciarSesion ************** //
		jugador.setApodo("sbraceras");
		jugador.setPassword("TaTObrA");

		try {
			ServicioCentral.getInstance().iniciarSesion(jugador);
		} catch (JugadorException e) {
			System.err.println(e.getMessage());
		}
		
		
		// ************** Crear Grupo ************** //
		
		jugador.setId(4);
		
		GrupoDTO grupo = new GrupoDTO();
		grupo.setNombre("UADE");
				
		ServicioCentral.getInstance().crearGrupo(grupo, jugador);
		
		// ************** Agregar Miembros a Grupos ************** //
		
	/*	ArrayList<JugadorDTO> jugadores = ServicioCentral.getInstance().obtenerJugadores();
		List<JugadorDTO> agregar = new ArrayList<JugadorDTO>();
		
		agregar.add(jugadores.get(1));
		agregar.add(jugadores.get(2));
		
		ServicioCentral.getInstance().agregarJugadorGrupo(agregar, grupo , jugador);*/

		
		// ************** Jugar Libre Individual ************** //
		
		
		JugadorDTO jugador1 = new JugadorDTO();
		JugadorDTO jugador2 = new JugadorDTO();
		JugadorDTO jugador3 = new JugadorDTO();
		JugadorDTO jugador4 = new JugadorDTO();
		
		// hardcodeamos los id porque deberian venir desde la vista cargados
		
		jugador1.setApodo("sbraceras");
		jugador1.setPassword("TaTObrA");
		jugador1.setId(14);
		
		
		jugador2.setApodo("cgodio");
		jugador2.setPassword("12345");
		jugador2.setId(1);
		
		jugador3.setApodo("GMasaro");
		jugador3.setPassword("banfieldcapo");
		jugador3.setId(3);
		
		jugador4.setApodo("OZamudio");
		jugador4.setPassword("yousomac");
		jugador4.setId(4);


		
		try {
			ServicioCentral.getInstance().iniciarSesion(jugador1);
		} catch (JugadorException e) {
			System.err.println(e.getMessage());
		}
		try {
			ServicioCentral.getInstance().iniciarSesion(jugador2);
		} catch (JugadorException e) {
			System.err.println(e.getMessage());
		}
		try {
			ServicioCentral.getInstance().iniciarSesion(jugador3);
		} catch (JugadorException e) {
			System.err.println(e.getMessage());
		}
		try {
			ServicioCentral.getInstance().iniciarSesion(jugador4);
		} catch (JugadorException e) {
			System.err.println(e.getMessage());
		}	

		PartidoDTO part1 = ServicioCentral.getInstance().jugarLibreIndividual(jugador1);
		PartidoDTO part2 = ServicioCentral.getInstance().jugarLibreIndividual(jugador2);
		PartidoDTO part3 = ServicioCentral.getInstance().jugarLibreIndividual(jugador3);
		PartidoDTO part4 = ServicioCentral.getInstance().jugarLibreIndividual(jugador4);
		
	}

}

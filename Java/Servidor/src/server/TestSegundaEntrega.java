package server;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bean.*;
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
		jugador1.setApodo("AOrion");
		jugador1.setPassword("lesiono4");

//		jugador2.setId(4);
		jugador2.setApodo("LGonzalez");
		jugador2.setPassword("yanosoyelmismo");

//		jugador3.setId(3);
		jugador3.setApodo("JMaidana");	
		jugador3.setPassword("alaseleccion");
		
//		jugador4.setId(15);
		jugador4.setApodo("LMessi");
		jugador4.setPassword("balonDeOro");

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
			jugador4 = ServicioCentral.getInstance().iniciarSesion(jugador4);
		} catch (JugadorException e) {
			System.err.println(e.getMessage());
		}	

		// ************** Controlamos si se armo Partido ************** //
		System.out.println("");

		PartidoDTO part1 = ServicioCentral.getInstance().jugarLibreIndividual(jugador1);
		PartidoDTO part2 = ServicioCentral.getInstance().jugarLibreIndividual(jugador2);
		PartidoDTO part3 = ServicioCentral.getInstance().jugarLibreIndividual(jugador3);
		PartidoDTO part4 = ServicioCentral.getInstance().jugarLibreIndividual(jugador4);
				
		if (part4 != null) {
			System.out.println("Se armo un partido! Inicio: " + part4.getFechaInicio());
			System.out.println("El partido nuevo es: " + part4.getId());
		}

		if (part3 == null) {
			List<PartidoDTO> partidosPendientes = ServicioCentral.getInstance().tengoPartido(jugador3);
			if (!partidosPendientes.isEmpty()){
				part3 = partidosPendientes.get(partidosPendientes.size()-1);
				System.out.println("El partido nuevo es: " + part3.getId());
			}
		}

		if (part2 == null) {
			List<PartidoDTO> partidosPendientes = ServicioCentral.getInstance().tengoPartido(jugador2);
			if (!partidosPendientes.isEmpty()){
				part2 = partidosPendientes.get(partidosPendientes.size()-1);
				System.out.println("El partido nuevo es: " + part2.getId());
			}
		}

		if (part1 == null) {
			List<PartidoDTO> partidosPendientes = ServicioCentral.getInstance().tengoPartido(jugador1);
			if (!partidosPendientes.isEmpty()){
				part1 = partidosPendientes.get(partidosPendientes.size()-1);
				System.out.println("El partido nuevo es: " + part1.getId());
			}
		}

		// ************** Obtenemos las cartas de los Jugadores ************** //
		System.out.println("");

		List<CartaJugadorDTO> cartasJugador1 = new ArrayList<CartaJugadorDTO>();
		List<CartaJugadorDTO> cartasJugador2 = new ArrayList<CartaJugadorDTO>();
		List<CartaJugadorDTO> cartasJugador3 = new ArrayList<CartaJugadorDTO>();
		List<CartaJugadorDTO> cartasJugador4 = new ArrayList<CartaJugadorDTO>();
		try {
			cartasJugador1 = ServicioCentral.getInstance().obtenerCartasJugador(part1, jugador1);
			cartasJugador2 = ServicioCentral.getInstance().obtenerCartasJugador(part2, jugador2);
			cartasJugador3 = ServicioCentral.getInstance().obtenerCartasJugador(part3, jugador3);
			cartasJugador4 = ServicioCentral.getInstance().obtenerCartasJugador(part4, jugador4);

			System.out.println("Cartas del jugador " + jugador1.getApodo());
			for(CartaJugadorDTO cartaJugador: cartasJugador1) {
				System.out.println("  Puede tirar: " + cartaJugador.getCarta().toString());
			}
			System.out.println("");

			System.out.println("Cartas del jugador " + jugador2.getApodo());
			for(CartaJugadorDTO cartaJugador: cartasJugador2) {
				System.out.println("  Puede tirar: " + cartaJugador.getCarta().toString());
			}
			System.out.println("");

			System.out.println("Cartas del jugador " + jugador3.getApodo());
			for(CartaJugadorDTO cartaJugador: cartasJugador3) {
				System.out.println("  Puede tirar: " + cartaJugador.getCarta().toString());
			}
			System.out.println("");

			System.out.println("Cartas del jugador " + jugador4.getApodo());
			for(CartaJugadorDTO cartaJugador: cartasJugador4) {
				System.out.println("  Puede tirar: " + cartaJugador.getCarta().toString());
			}
			System.out.println("");

		} catch (ControladorException | PartidoException e) {
			e.printStackTrace();
		}


		// ************** EMPEZAMOS A JUGAR !!! ************** //
		System.out.println("");

		JugadorDTO turnoJugador = new JugadorDTO();
		try {
			turnoJugador = ServicioCentral.getInstance().obtenerJugadorActual(part1, jugador1);
			System.out.println("Le toca jugar a: " + turnoJugador.getApodo());
		} catch (PartidoException | ControladorException e) {
			e.printStackTrace();
		}

		// ************** Obtenemos los cantos Posibles del jugador actual ************** //
		System.out.println("");

		try {
			List<TipoEnvite> opciones = ServicioCentral.getInstance().obtenerEnvitesDisponibles(part1, turnoJugador);
			if (opciones.isEmpty()) {
				System.out.println("El jugador " + turnoJugador.getApodo() +  " no puede cantar nada!");
			} else {
				for(TipoEnvite envite: opciones) {
					System.out.println("El jugador " + turnoJugador.getApodo() + " puede cantar: " + envite.name());
				}
			}
		} catch (ControladorException | PartidoException e) {
			e.printStackTrace();
		}

		CartaTiradaDTO cartaTirada = new CartaTiradaDTO();
		cartaTirada.setFechaHora(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		if (turnoJugador.getApodo().equals(jugador1.getApodo())) {
			// tiramos la primer carta del jugador1
			cartaTirada.setCartaJugador(cartasJugador1.get(0));
		} else if (turnoJugador.getApodo().equals(jugador2.getApodo())) {
			cartaTirada.setCartaJugador(cartasJugador2.get(0));
		} else if (turnoJugador.getApodo().equals(jugador3.getApodo())) {
			cartaTirada.setCartaJugador(cartasJugador3.get(0));
		} else if (turnoJugador.getApodo().equals(jugador4.getApodo())) {
			cartaTirada.setCartaJugador(cartasJugador4.get(0));
		}
		try {
			System.out.println("");
			System.out.println("Voy a tirar la Carta: " + cartaTirada.getCartaJugador().getCarta().toString());
			ServicioCentral.getInstance().nuevoMovimientoPartido(part3, turnoJugador, cartaTirada);
		} catch (ControladorException | PartidoException | BazaException e) {
			e.printStackTrace();
		}
				
		// ************** SIMULAMOS EL SEGUNDO MOVIMIENTO DE LA BAZA ************** //
		System.out.println("");
		
		turnoJugador = new JugadorDTO();
		try {
			turnoJugador = ServicioCentral.getInstance().obtenerJugadorActual(part1, jugador1);
			System.out.println("Le toca jugar a: " + turnoJugador.getApodo());
		} catch (PartidoException | ControladorException e) {
			e.printStackTrace();
		}

		// ************** Obtenemos los cantos Posibles del jugador actual ************** //

		try {
			List<TipoEnvite> opciones = ServicioCentral.getInstance().obtenerEnvitesDisponibles(part2, turnoJugador);
			if (opciones.isEmpty()) {
				System.out.println("El jugador " + turnoJugador.getApodo() +  " no puede cantar nada!");
			} else {
				for(TipoEnvite envite: opciones) {
					System.out.println("El jugador " + turnoJugador.getApodo() + " puede cantar: " + envite.name());
				}
			}
		} catch (ControladorException | PartidoException e) {
			e.printStackTrace();
		}

		cartaTirada = new CartaTiradaDTO();
		cartaTirada.setFechaHora(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		if (turnoJugador.getApodo().equals(jugador1.getApodo())) {
			// tiramos la segunda carta del jugador1
			cartaTirada.setCartaJugador(cartasJugador1.get(1));
		} else if (turnoJugador.getApodo().equals(jugador2.getApodo())) {
			cartaTirada.setCartaJugador(cartasJugador2.get(1));
		} else if (turnoJugador.getApodo().equals(jugador3.getApodo())) {
			cartaTirada.setCartaJugador(cartasJugador3.get(1));
		} else if (turnoJugador.getApodo().equals(jugador4.getApodo())) {
			cartaTirada.setCartaJugador(cartasJugador4.get(1));
		}
		try {
			System.out.println("Voy a tirar la Carta: " + cartaTirada.getCartaJugador().getCarta().toString());
			ServicioCentral.getInstance().nuevoMovimientoPartido(part3, turnoJugador, cartaTirada);
		} catch (ControladorException | PartidoException | BazaException e) {
			e.printStackTrace();
		}
		

		// ************** Intentamos tirar una carta y NO es el turno del jugador! ************** //

		cartaTirada.setFechaHora(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		cartaTirada.setCartaJugador(cartasJugador4.get(1));
		try {
			System.out.println("Trata de Tirar el Jugador " + jugador4.getApodo() + " el " + cartaTirada.getCartaJugador().getCarta().toString());
			ServicioCentral.getInstance().nuevoMovimientoPartido(part4, jugador4, cartaTirada);
		} catch (ControladorException | PartidoException | BazaException e) {
			e.printStackTrace();
		}

		// ************** SIMULAMOS EL TERCER MOVIMIENTO DE LA BAZA ************** //
		turnoJugador = new JugadorDTO();
		try {
			turnoJugador = ServicioCentral.getInstance().obtenerJugadorActual(part1, jugador1);
			System.out.println("Le toca jugar a: " + turnoJugador.getApodo());
		} catch (PartidoException | ControladorException e) {
			e.printStackTrace();
		}

		// ************** Obtenemos los cantos Posibles del jugador actual ************** //

		List<TipoEnvite> opciones;
		try {
			opciones = ServicioCentral.getInstance().obtenerEnvitesDisponibles(part2, turnoJugador);
			if (opciones.isEmpty()) {
				System.out.println("El jugador " + turnoJugador.getApodo() +  " no puede cantar nada!");
			} else {
				for(TipoEnvite envite: opciones) {
					System.out.println("El jugador " + turnoJugador.getApodo() + " puede cantar: " + envite.name());
				}
			}
		} catch (ControladorException | PartidoException e) {
			e.printStackTrace();
		}
		
		// ************** SE TIENE QUE PODER CANTAR ENVIDO ************** //
	
		EnviteDTO envite = new EnviteDTO();
		envite.setTipoEnvite(TipoEnvite.Envido);
		envite.setFechaHora(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		
		try {
			ServicioCentral.getInstance().nuevoMovimientoPartido(part1, turnoJugador, envite);
		} catch (BazaException | ControladorException | PartidoException e) {
			
			e.printStackTrace();
		}
		
		// ************** TENDRIA QUE PODER CONTESTAR ALGUIEN ************** //
	
		turnoJugador = new JugadorDTO();
		try {
			turnoJugador = ServicioCentral.getInstance().obtenerJugadorActual(part1, jugador1);
			System.out.println("Le toca contestar a: " + turnoJugador.getApodo());
		} catch (PartidoException | ControladorException e) {
			e.printStackTrace();
		}

		
		// ************** Obtenemos los cantos Posibles del jugador actual ************** //

		try {
			opciones = ServicioCentral.getInstance().obtenerEnvitesDisponibles(part2, turnoJugador);
			if (opciones.isEmpty()) {
				System.out.println("El jugador " + turnoJugador.getApodo() +  " no puede cantar nada!");
			} else {
				for(TipoEnvite tipoEnvite: opciones) {
					System.out.println("El jugador " + turnoJugador.getApodo() + " puede cantar: " + tipoEnvite.name());
				}
			}
		} catch (ControladorException | PartidoException e) {
			e.printStackTrace();
		}
		
		
		
		/* PROBAMOS HASTA ACA *//////
		
		

	













		// ************** Se Prueba Obtener un Partido ************** //
		PartidoDTO partidoObtenido = new PartidoDTO();
		partidoObtenido.setId(46);
	
		try {
			Partido partido = ServicioCentral.getInstance().obtenerPartido(partidoObtenido);
			System.out.println("Se Obtuvo el Partido ID: "+ partido.getId());
		} catch (PartidoException e) {
			e.printStackTrace();
		}

		
		// ************** Se Prueba Obtener Partidos de un Jugador Entre Fechas ************** //
		
		/* DESARROLLAR */
		
		
	}

}

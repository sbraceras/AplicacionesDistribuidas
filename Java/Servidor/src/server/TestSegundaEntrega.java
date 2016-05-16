package server;

import controlador.ServicioCentral;
import dtos.*;
import exceptions.JugadorException;

public class TestSegundaEntrega {

	public TestSegundaEntrega() {

		//************** registrarJugador **************
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

		
	}

}

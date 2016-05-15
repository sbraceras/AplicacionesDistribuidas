package gui;

import java.util.ArrayList;

import dtos.*;
import enums.TipoEnvite;

public class VentanaJugar extends ObserverRemoto {

	ArrayList<TipoEnvite> envitesPosibles;
	ArrayList<CartaJugadorDTO> cartasEnMesa;
	ArrayList<CartaJugadorDTO> misCartas;
	JugadorDTO yo;
	JugadorDTO jugadorActual;
	int puntajePareja1;
	int puntajePareja2;
	ArrayList<JugadorDTO> posicionesMesa;
	int numeroBaza;

}


public void update(NuevoJuegoDTO nuevoJuego) //se ejecuta al iniciar partido
{
	
	
}

public void update(NuevoChicoDTO nuevoChico) //se ejecuta al empezar un nuevo chico
{
	/* reinicia puntajes, avisa quien gano cada uno, se limpian cartas en mesa, etc. */
	
}

public void update (NuevaManoDTO nuevaMano){

}

public void update (NuevoMovimientoDTO nuevoMovimiento){
	
	puntajePareja1 = nuevoMovimiento.getPuntajePareja1();
	puntajePareja2 = nuevoMovimiento.getPuntajePareja2();
	numeroBaza = nuevoMovimiento.getNumeroBaza();
	
	
		if(!nuevoMovimiento.esEnvite()) /* esto significa que alguien tiro una carta */
		{
			cartasEnMesa.add(nuevoMovimiento.getCartaTirada());
			/* actualizar en pantalla que se tiro esta carta*/
			jugadorActual = nuevoMovimiento.getJugadorActual(); /* ME dice a quien le toca jugar */
		
			if(jugadorActual.getId() == yo.getId()) /*si no soy mano, no hago nada */
			{
				habilitarBotonesJugar();
				refrescarOpcionesDiponibles (NuevoMovmientoDTO.getOpcionesDisponibles()); /* Le pasa la collection de envites */
						
			}
		}
		else 
		{
			if(nuevoMovimiento.getPie1().getId() == yo.getId() || nuevoMovimiento.getPie2().getId() == yo.getId()) 
			{
				/* si es el pie debe contestar si o si, no habilita jugar solo contestar */
				refrescarOpcionesDiponibles (NuevoMovmientoDTO.getOpcionesDisponibles()); /* Le pasa la collection de envites */
			}
		}
}


	
	


	public void update (String mensaje){ //el servidor se encarga de mandar un mensaje distinto a cada mensaje
	
		
	}

}

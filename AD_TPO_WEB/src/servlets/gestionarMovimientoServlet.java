package servlets;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessDelegate.BusinessDelegate;
import dtos.CartaJugadorDTO;
import dtos.CartaTiradaDTO;
import dtos.EnviteDTO;
import dtos.JugadorDTO;
import dtos.ManoDTO;
import dtos.ParejaDTO;
import dtos.PartidoDTO;
import dtos.PuntajeParejaDTO;
import enums.EstadoPartido;
import enums.TipoEnvite;

/**
 * Servlet implementation class gestionarMovimientoServlet
 */

@WebServlet("/gestionarMovimiento")
public class gestionarMovimientoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   	private static BusinessDelegate bd;
	
	
    public gestionarMovimientoServlet() {
        super();
    }

    public void init() throws ServletException {
    	super.init();
    	try {
			bd = new BusinessDelegate();
		} catch (RemoteException e) {
		    throw new ServletException(e);
		}
    }
    
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idJugador = Integer.valueOf(request.getParameter("idJugador")).intValue();
		String apodoJugador = request.getParameter("apodoJugador");

		String movimiento = request.getParameter("movimiento");
		int idPartido = Integer.valueOf(request.getParameter("idPartido"));

		JugadorDTO jugador = new JugadorDTO();
		jugador.setId(idJugador);
		jugador.setApodo(apodoJugador);

		PartidoDTO partido = new PartidoDTO();
		partido.setId(idPartido);

		List<CartaJugadorDTO> misCartas = bd.obtenerCartasJugador(partido, jugador);

		if(movimiento.equals("env")) {
			String tipoEnvite = request.getParameter("nombreEnvite");
			
			EnviteDTO envite = new EnviteDTO();
			envite.setFechaHora(new Timestamp (Calendar.getInstance().getTimeInMillis()));
			envite.setTipoEnvite(TipoEnvite.obtenerPorTipo(tipoEnvite));
			try {
				bd.nuevoMovimientoPartido(partido, jugador, envite);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		} else if(movimiento.equals("ct")) {
			int idCartaTirada = Integer.valueOf(request.getParameter("idCartaTirada"));

			CartaTiradaDTO cartaTirada = new CartaTiradaDTO();
			CartaJugadorDTO cartaJugador = null;

			// obtengo el DTO de la carta que acaba de tirar (con todos sus datos)
			for (CartaJugadorDTO miCarta: misCartas) {
				if (miCarta.getCarta().getId() == idCartaTirada) {
					miCarta.setTirada(true);// ya seteo que esta tirada para no tener que llamar de nuevo 
											// al metodo 'obtenerCartasJugador' del BusinessDelegate! De esta manera
											// ya le paso a la pagina 'ventanaJuego.jsp' la List 'misCartas' actualizada!!! 
					cartaJugador = miCarta;
					break;
				}
			}
			if (cartaJugador == null) {
				throw new ServletException("Error grave: no se encontro el id de la carta tirada");
			}

			cartaTirada.setCartaJugador(cartaJugador);
			cartaTirada.setFechaHora(new Timestamp (Calendar.getInstance().getTimeInMillis()));

			try {
				bd.nuevoMovimientoPartido(partido, jugador, cartaTirada);
			} catch (RemoteException e) {
				e.printStackTrace();
			}				
		}

		RequestDispatcher rd = null;

		try {
			if (bd.partidoEstaTerminado(partido, jugador)) {
				//Se termino el partido, True es 1
				request.setAttribute("estadoPartido", EstadoPartido.Terminado);
				
//				request.setAttribute("parejaGanadora", );
				// OBTENER QUIEN GANO EL PARTIDO
			}
			else
			{
				// El partido continua! Le vuelvo a pasar toda la información, refrescada 

				request.setAttribute("estadoPartido", EstadoPartido.Empezado);

				JugadorDTO jugadorActual = bd.obtenerJugadorActual(partido, jugador);
				misCartas = bd.obtenerCartasJugador(partido, jugador); // pido de nuevo mis Cartas ya que quizas se creo una nueva mano!
				List<PuntajeParejaDTO> puntajes = bd.obtenerPuntajeChico(partido, jugador);
				List<ParejaDTO> parejas = bd.obtenerParejasPartido(partido);
				ManoDTO ultimaMano = bd.obtenerUltimaManoActiva(partido, jugador);
				List<JugadorDTO> ganadoresBazas = bd.obtenerGanadoresBazas(partido, jugador);

				request.setAttribute("miPartido", partido);
				request.setAttribute("jugador", jugador);
				request.setAttribute("jugadorActual", jugadorActual);
				request.setAttribute("parejas", parejas);
				request.setAttribute("misCartas", misCartas);
				request.setAttribute("puntajes", puntajes);
				request.setAttribute("ganadoresBazas", ganadoresBazas);
				request.setAttribute("bazas", ultimaMano.getBazas());
				
				if (idJugador == jugadorActual.getId()) {
					// le toca jugar a quien envio la peticion!
					List<TipoEnvite> envites = bd.obtenerEnvitesDisponibles(partido, jugador);
					request.setAttribute("envites", envites);
				} else {
					request.setAttribute("envites", new ArrayList<TipoEnvite>());
				}
			}
			
			rd = getServletContext().getRequestDispatcher("/ventanaJuego.jsp");
			rd.forward(request, response);

		} catch (RemoteException e) {
			e.printStackTrace();
		}	
		
	}
}

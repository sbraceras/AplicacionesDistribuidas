package servlets;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessDelegate.BusinessDelegate;
import dtos.CartaJugadorDTO;
import dtos.JugadorDTO;
import dtos.ManoDTO;
import dtos.MovimientoDTO;
import dtos.ParejaDTO;
import dtos.PartidoDTO;
import dtos.PuntajeParejaDTO;
import enums.EstadoPartido;
import enums.TipoEnvite;
import enums.TipoPartido;

/**
 * Servlet implementation class CrearPartidaParejasServlet
 */

public class CrearPartidaParejasServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static BusinessDelegate bd;
 
    public CrearPartidaParejasServlet() {
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
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idJugador = Integer.valueOf(request.getParameter("idJugador")).intValue();
		String apodoJugador = request.getParameter("apodoJugador");
//		int idJugador2 = Integer.valueOf(request.getParameter("idJugador2")).intValue();
		String apodoJugador2 = request.getParameter("apodoJugador2");

		JugadorDTO jugador = new JugadorDTO();
		jugador.setId(idJugador);
		jugador.setApodo(apodoJugador);
		JugadorDTO jugador2 = new JugadorDTO();
//		jugador2.setId(idJugador2);
		jugador2.setApodo(apodoJugador2);
		
		ParejaDTO pareja = new ParejaDTO();
		pareja.setJugador1(apodoJugador);
		pareja.setJugador2(apodoJugador2);
		
		RequestDispatcher rd = null;
		request.setAttribute("jugador", jugador);
		
		try {
			
			System.out.println("Llamo al business delegate");
			request.setAttribute("jugador", jugador);
			request.setAttribute("jugador2", jugador2);
			PartidoDTO miPartido = bd.jugarLibreParejas(pareja);
			
			if (miPartido == null) {
				// aun no se armo el partido en esa modalidad
				PartidoDTO ultimoPartido = bd.obtenerUltimoPartidoPendienteModalidad(TipoPartido.LibreParejas, jugador);
				// enviamos el ultimo identificador de partido para que pueda obtener el partido nuevo!
				request.setAttribute("idUltimoPartido", ultimoPartido == null ? 0 : ultimoPartido.getId());
				request.setAttribute("tipoPartido", TipoPartido.LibreParejas);
				rd = request.getRequestDispatcher("ventanaEsperandoPartido.jsp");
			} else {
				// le pasamos a la pagina todos los parametros de juego que se necesitan
				JugadorDTO jugadorActual = bd.obtenerJugadorActual(miPartido, jugador);
				List<CartaJugadorDTO> misCartas = bd.obtenerCartasJugador(miPartido, jugador);
				List<PuntajeParejaDTO> puntajes = bd.obtenerPuntajeChico(miPartido, jugador);
				List<JugadorDTO> ganadoresBazas = bd.obtenerGanadoresBazas(miPartido, jugador);
				ManoDTO ultimaMano = bd.obtenerUltimaManoActiva(miPartido, jugador);
				List<MovimientoDTO> movimientos = bd.obtenerMovimientosUltimaBaza(miPartido, jugador);

				
				request.setAttribute("miPartido", miPartido);
				request.setAttribute("jugadorActual", jugadorActual);
				request.setAttribute("parejas", miPartido.getParejas());
				request.setAttribute("misCartas", misCartas);
				request.setAttribute("puntajes", puntajes);
				request.setAttribute("estadoPartido", EstadoPartido.Empezado);
				request.setAttribute("bazas", ultimaMano.getBazas());
				request.setAttribute("ganadoresBazas", ganadoresBazas);
				request.setAttribute("movimientos", movimientos);

				List<TipoEnvite> envites = new ArrayList<TipoEnvite>();
				request.setAttribute("envites", envites);
				rd = request.getRequestDispatcher("/ventanaJuego.jsp");
			}
			
			rd.forward(request, response);

		} catch (Exception e) {
			
			rd = request.getRequestDispatcher("/ventanaEsperandoPartido.jsp");
			rd.forward(request, response);
		}
		
	}
	
}




package servlets;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessDelegate.BusinessDelegate;
import dtos.CartaJugadorDTO;
import dtos.JugadorDTO;
import dtos.ManoDTO;
import dtos.PartidoDTO;
import dtos.PuntajeParejaDTO;
import enums.EstadoPartido;
import enums.TipoEnvite;
import enums.TipoPartido;

/**
 * Servlet implementation class esperandoPartido
 */
@WebServlet("/EsperandoPartido")
public class EsperandoPartidoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static BusinessDelegate bd;
 
    public EsperandoPartidoServlet() {
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
		int idUltimoPartido = Integer.valueOf(request.getParameter("idUltimoPartido"));
		TipoPartido tipoPartido = TipoPartido.obtenerPorTipo(request.getParameter("tipoPartido"));
		int idJugador = Integer.valueOf(request.getParameter("idJugador")).intValue();
		String apodoJugador = request.getParameter("apodoJugador");

		JugadorDTO jugador = new JugadorDTO();
		jugador.setId(idJugador);
		jugador.setApodo(apodoJugador);

		try {

			RequestDispatcher rd = null;

			PartidoDTO ultimoPartido = bd.obtenerUltimoPartidoPendienteModalidad(tipoPartido, jugador);
			
			if ((ultimoPartido != null) && (ultimoPartido.getId() > idUltimoPartido)) {
				// encontramos un partido nuevo!
				JugadorDTO jugadorActual = bd.obtenerJugadorActual(ultimoPartido, jugador);
				List<CartaJugadorDTO> misCartas = bd.obtenerCartasJugador(ultimoPartido, jugador);
				List<PuntajeParejaDTO> puntajes = bd.obtenerPuntajeChico(ultimoPartido, jugador);
				ManoDTO ultimaMano = bd.obtenerUltimaManoActiva(ultimoPartido, jugador);
				List<JugadorDTO> ganadoresBazas = bd.obtenerGanadoresBazas(ultimoPartido, jugador);
				
				request.setAttribute("miPartido", ultimoPartido);
				request.setAttribute("jugador", jugador);
				request.setAttribute("jugadorActual", jugadorActual);
				request.setAttribute("parejas", ultimoPartido.getParejas());
				request.setAttribute("misCartas", misCartas);
				request.setAttribute("puntajes", puntajes);
				request.setAttribute("estadoPartido", EstadoPartido.Empezado);
				request.setAttribute("ganadoresBazas", ganadoresBazas);
				request.setAttribute("bazas", ultimaMano.getBazas());

				//La inicio vacia ya que no hay envites por jugar apenas comienza
				request.setAttribute("envites", new ArrayList<TipoEnvite>());

				rd = getServletContext().getRequestDispatcher("/ventanaJuego.jsp");
				rd.forward(request, response);
			} else {
				// puede tener partidos pendientes en esa modalidad, pero aun no se encontro uno nuevo!!!
				request.setAttribute("jugador", jugador);
				request.setAttribute("idUltimoPartido", idUltimoPartido);
				request.setAttribute("tipoPartido", tipoPartido);

				rd = getServletContext().getRequestDispatcher("/ventanaEsperandoPartido.jsp");
				rd.forward(request, response);
			}

		} catch (RemoteException e) {
			
		}
	}
}

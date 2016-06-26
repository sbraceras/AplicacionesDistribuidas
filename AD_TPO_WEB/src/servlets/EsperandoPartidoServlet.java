package servlets;

import java.io.IOException;
import java.rmi.RemoteException;
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
import dtos.PartidoDTO;
import dtos.PuntajeParejaDTO;
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
        // TODO Auto-generated constructor stub
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
			if (ultimoPartido.getId() > idUltimoPartido) {
				// encontramos un partido nuevo!
				JugadorDTO jugadorActual = bd.obtenerJugadorActual(ultimoPartido, jugador);
				List<CartaJugadorDTO> misCartas = bd.obtenerCartasJugador(ultimoPartido, jugador);
				List<PuntajeParejaDTO> puntajes = bd.obtenerPuntajeChico(ultimoPartido, jugador);
				
				rd = getServletContext().getRequestDispatcher("/ventanaJuego.jsp");

				request.setAttribute("miPartido", ultimoPartido);
				request.setAttribute("jugador", jugador);
				request.setAttribute("jugadorActual", jugadorActual);
				request.setAttribute("parejas", ultimoPartido.getParejas());
				request.setAttribute("misCartas", misCartas);
				request.setAttribute("puntajes", puntajes);



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

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
import dtos.MovimientoDTO;
import dtos.PartidoDTO;
import dtos.PuntajeParejaDTO;
import enums.EstadoPartido;
import enums.TipoEnvite;
import enums.TipoPartido;

/**
 * Servlet implementation class CrearPartidaIndividualServlet
 */

@WebServlet("/CrearPartidaIndividualServlet")
public class CrearPartidaIndividualServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static BusinessDelegate bd;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearPartidaIndividualServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    @Override
    public void init() throws ServletException {
    	super.init();
    	try {
			bd = new BusinessDelegate();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
		    throw new ServletException(e);
		}
    }
    

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String apodoJugador = request.getParameter("apodoJugador");
		int idJugador = Integer.valueOf(request.getParameter("idJugador")).intValue();

		JugadorDTO jg = new JugadorDTO();

		jg.setApodo(apodoJugador);
		jg.setId(idJugador);

		try {
			RequestDispatcher rd;

			PartidoDTO miPartido = bd.jugarLibreIndividual(jg);

//			session.setAttribute("user", jg);
//			session.setAttribute("userId", jg.getApodo());

			request.setAttribute("jugador", jg);
			
			if (miPartido == null) {
				// aun no se armo el partido en esa modalidad
				PartidoDTO ultimoPartido = bd.obtenerUltimoPartidoPendienteModalidad(TipoPartido.LibreIndividual, jg);
				// enviamos el ultimo identificador de partido para que pueda obtener el partido nuevo!
				request.setAttribute("idUltimoPartido", ultimoPartido == null ? 0 : ultimoPartido.getId());
				request.setAttribute("tipoPartido", TipoPartido.LibreIndividual);
				
				rd = request.getRequestDispatcher("/ventanaEsperandoPartido.jsp");
			} else {
				// le pasamos a la pagina todos los parametros de juego que se necesitan
				JugadorDTO jugadorActual = bd.obtenerJugadorActual(miPartido, jg);
				List<CartaJugadorDTO> misCartas = bd.obtenerCartasJugador(miPartido, jg);
				List<PuntajeParejaDTO> puntajes = bd.obtenerPuntajeChico(miPartido, jg);
				List<JugadorDTO> ganadoresBazas = bd.obtenerGanadoresBazas(miPartido, jg);
				ManoDTO ultimaMano = bd.obtenerUltimaManoActiva(miPartido, jg);
				List<MovimientoDTO> movimientos = bd.obtenerMovimientosUltimaBaza(miPartido, jg);

				
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

			// OJO!!! Aca debe enviarnos a "ventanaEsperandoPartido.jsp"  ?????

			RequestDispatcher rd = request.getRequestDispatcher("/ventanaEsperandoPartido.jsp");
			rd.forward(request, response);
		}
		
	}
		


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}

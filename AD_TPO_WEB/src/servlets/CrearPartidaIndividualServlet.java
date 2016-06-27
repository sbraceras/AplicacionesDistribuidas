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
import javax.servlet.http.HttpSession;

import businessDelegate.BusinessDelegate;
import dtos.CartaJugadorDTO;
import dtos.JugadorDTO;
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
		
		JugadorDTO jg = new JugadorDTO();
		
		HttpSession session = request.getSession(true);
		
		//devuelve la session asociada a la session http o una nueva session 
		//si no hay ningun identificador en la session http.
		
		jg = (JugadorDTO) session.getAttribute("user");
		
		try {
			RequestDispatcher rd;
			System.out.println("Llamo al business delegate");
			PartidoDTO miPartido = bd.jugarLibreIndividual(jg);

			
			if (miPartido == null) {
				// aun no se armo el partido en esa modalidad
				PartidoDTO ultimoPartido = bd.obtenerUltimoPartidoPendienteModalidad(TipoPartido.LibreIndividual, jg);
				// enviamos el ultimo identificador de partido para que pueda obtener el partido nuevo!
				request.setAttribute("idUltimoPartido", ultimoPartido == null ? 0 : ultimoPartido.getId());
				request.setAttribute("tipoPartido", TipoPartido.LibreIndividual);
				request.setAttribute("jugador", jg);
				rd = request.getRequestDispatcher("ventanaEsperandoPartido.jsp");
			} else {
				// le pasamos a la pagina todos los parametros de juego que se necesitan
				JugadorDTO jugadorActual = bd.obtenerJugadorActual(miPartido, jg);
				List<CartaJugadorDTO> misCartas = bd.obtenerCartasJugador(miPartido, jg);
				List<PuntajeParejaDTO> puntajes = bd.obtenerPuntajeChico(miPartido, jg);
				
				request.setAttribute("miPartido", miPartido);
				request.setAttribute("jugadorActual", jugadorActual);
				request.setAttribute("parejas", miPartido.getParejas());
				request.setAttribute("misCartas", misCartas);
				request.setAttribute("puntajes", puntajes);
				request.setAttribute("estadoPartido", EstadoPartido.Empezado);

				List<TipoEnvite> envites = new ArrayList<TipoEnvite>();
				request.setAttribute("envites", envites);
				rd = request.getRequestDispatcher("/ventanaJuego.jsp");
			}
			
			rd.forward(request, response);

		} catch (Exception e) {
			
			RequestDispatcher rd = request.getRequestDispatcher("/ventanaEsperandoPartido.jsp");
			rd.forward(request, response);
		}
		
	}
		


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}

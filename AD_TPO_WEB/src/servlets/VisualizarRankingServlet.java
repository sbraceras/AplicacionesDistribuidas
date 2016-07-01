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

import dtos.JugadorDTO;
import dtos.PartidoDTO;
import businessDelegate.BusinessDelegate;

/**
 * Servlet implementation class VisualizarRankingServlet
 */

@WebServlet ("/VisualizarRanking")
public class VisualizarRankingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static BusinessDelegate bd;
       

	
	public VisualizarRankingServlet() {
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
		JugadorDTO jugador = new JugadorDTO();
		jugador.setApodo(apodoJugador);
		jugador.setId(idJugador);
		
		try {
			
			RequestDispatcher rd = null;
			List<PartidoDTO> partidos = bd.levantarPartidosTerminadosJugador(jugador);
			jugador= bd.obtenerJugadorCompleto(jugador);
			request.setAttribute("partidosTerminados", partidos);
			request.setAttribute("jugador", jugador);

			rd = getServletContext().getRequestDispatcher("/ventanaRankingJugador.jsp");
			rd.forward(request, response);
			
		} catch (RemoteException e)
		{	
			//Transportar bien el error
			////////////////
			//////////
			e.printStackTrace();
		}
		
		
	}

}

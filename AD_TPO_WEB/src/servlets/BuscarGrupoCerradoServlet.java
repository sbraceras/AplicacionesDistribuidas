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
import dtos.GrupoDTO;
import dtos.JugadorDTO;

/**
 * Servlet implementation class BuscarGrupoCerradoServlet
 */
@WebServlet("/BuscarGrupoCerrado")
public class BuscarGrupoCerradoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	private static BusinessDelegate bd;
    public void init() throws ServletException {
    	super.init();
    	try {
			bd = new BusinessDelegate();
		} catch (RemoteException e) {
		    throw new ServletException(e);
		}
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
		
		RequestDispatcher rd = null;
		try {
						
			//Busco al jugador con toda la informacion que lleva consigo//
			jugador= bd.obtenerJugadorCompleto(jugador);
			
			
			request.setAttribute("jugador", jugador);
			
			rd = getServletContext().getRequestDispatcher("/buscarGrupoCerrado.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {

			rd = request.getRequestDispatcher("/main.jsp");
			rd.forward(request, response);
		}
		
	}



    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuscarGrupoCerradoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
}
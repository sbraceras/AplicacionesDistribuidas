package servlets;

import java.io.IOException;
import java.rmi.RemoteException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import businessDelegate.BusinessDelegate;
import dtos.JugadorDTO;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static BusinessDelegate bd;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
		}	
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
	
		String apodoJugador = request.getParameter("apodoJugador");
		int idJugador = Integer.valueOf(request.getParameter("idJugador")).intValue();

		JugadorDTO jg = new JugadorDTO();
		jg.setApodo(apodoJugador);
		jg.setId(idJugador);
		
		RequestDispatcher rd= null;
		
		try{
			bd.cerrarSesion(jg);
			session.invalidate();
					
			request.setAttribute("jugador", null);
			rd = request.getRequestDispatcher("/index.jsp");
		} catch (Exception e){
			rd = request.getRequestDispatcher("/index.jsp");
		}
		rd.forward(request, response);
	}
}
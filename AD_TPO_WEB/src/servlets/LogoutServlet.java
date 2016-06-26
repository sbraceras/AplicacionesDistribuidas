package servlets;

import java.io.IOException;
import java.rmi.RemoteException;

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
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		JugadorDTO jg = (JugadorDTO) session.getAttribute("user");
		
		if (jg!=null){
			
				bd.cerrarSesion(jg);
				session.invalidate();
				response.sendRedirect("index.jsp");
			
		}else{
			response.sendRedirect("index.jsp");
		}
	}	
}
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
 * Servlet implementation class Login
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static BusinessDelegate bd;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String apodo = request.getParameter("apodo");
		String contrasena = request.getParameter("contrasena");
		
		JugadorDTO jg = new JugadorDTO();
		jg.setApodo(apodo);
		jg.setPassword(contrasena);
		
		HttpSession session = request.getSession(true); // El metodo 'request.getSession(true)' devuelve la session asociada 
														// a la session http o una nueva session si no hay ningun identificador 
														// en la session http.
		if(session.isNew())
			System.out.println("Es una nueva session");
		else
			System.out.println("Ya existe una session para este cliente! El ID es: " + session.getId());

		session.removeAttribute("resultadoLogin");
		
		try {
			jg = bd.login(jg);

			request.setAttribute("jugador", jg);

			// OJO!!! NO VAMOS A ENVIAR MAS PARAMETROS A TRAVES DEL 'session'

//			session.setAttribute("user", jg);
//			session.setAttribute("userId", jg.getApodo());

//			response.sendRedirect("main.jsp");

			RequestDispatcher rd = null;
			rd = request.getRequestDispatcher("/main.jsp");

			rd.forward(request, response);
		} catch (Exception e) {
			session.setAttribute("resultadoLogin", false);
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doPost(request, response);
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}

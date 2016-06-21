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
import exceptions.JugadorException;

/**
 * Servlet implementation class Login
 */
@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static BusinessDelegate bd;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistroServlet() {
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
		String apodo = request.getParameter("apodoR");
		String contrasena = request.getParameter("contrasenaR");
		String mail = request.getParameter("mailR");
		
		JugadorDTO jg = new JugadorDTO();
		jg.setApodo(apodo);
		jg.setPassword(contrasena);
		jg.setMail(mail);
		
		
		HttpSession session = request.getSession(true);
		session.removeAttribute("resultado");
		
		try {
			bd.registrarJugador(jg);
			session.setAttribute("user", jg);
			session.setAttribute("userId", jg.getApodo());
			response.sendRedirect("main.jsp");
		} catch (Exception e) {
			session.setAttribute("resultadoRegistro", false);
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}
}

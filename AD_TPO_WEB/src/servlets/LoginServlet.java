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
import dtos.JugadorDTO;
import dtos.PartidoDTO;
import dtos.CartaJugadorDTO;
import dtos.PuntajeParejaDTO;
import enums.EstadoPartido;
import enums.TipoEnvite;
import enums.TipoPartido;

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
		
		HttpSession session = request.getSession(true);
		
		//devuelve la session asociada a la session http o una nueva session 
		//si no hay ningun identificador en la session http.
		if(session.isNew()){
			System.out.println("Es una nueva session");
		}
		else
			System.out.println("No es una nueva session, el id es:" + session.getId());
		
		session.removeAttribute("resultadoLogin");
		
		try {
			jg = bd.login(jg);


//			PartidoDTO miPartido = bd.jugarLibreIndividual(jg);
////			session.setAttribute("user", jg);
////			session.setAttribute("userId", jg.getApodo());
//
//			request.setAttribute("jugador", jg);
//
//			RequestDispatcher rd;
//			if (miPartido == null) {
//				// aun no se armo el partido en esa modalidad
//				PartidoDTO ultimoPartido = bd.obtenerUltimoPartidoPendienteModalidad(TipoPartido.LibreIndividual, jg);
//				// enviamos el ultimo identificador de partido para que pueda obtener el partido nuevo!
//				request.setAttribute("idUltimoPartido", ultimoPartido == null ? 0 : ultimoPartido.getId());
//				request.setAttribute("tipoPartido", TipoPartido.LibreIndividual);
//				rd = request.getRequestDispatcher("/ventanaEsperandoPartido.jsp");
//			} else {
//				// le pasamos a la pagina todos los parametros de juego que se necesitan
//				JugadorDTO jugadorActual = bd.obtenerJugadorActual(miPartido, jg);
//				List<CartaJugadorDTO> misCartas = bd.obtenerCartasJugador(miPartido, jg);
//				List<PuntajeParejaDTO> puntajes = bd.obtenerPuntajeChico(miPartido, jg);
//				List<JugadorDTO> ganadoresBazas = bd.obtenerGanadoresBazas(miPartido, jg);
//				
//				request.setAttribute("miPartido", miPartido);
//				request.setAttribute("jugadorActual", jugadorActual);
//				request.setAttribute("parejas", miPartido.getParejas());
//				request.setAttribute("misCartas", misCartas);
//				request.setAttribute("puntajes", puntajes);
//				request.setAttribute("estadoPartido", EstadoPartido.Empezado);
//				request.setAttribute("ganadoresBazas", ganadoresBazas);
//				
//				
//				//La inicio vacia ya que no hay envites por jugar apenas comienza
//				
//				List<TipoEnvite> envites = new ArrayList<TipoEnvite>();
//				request.setAttribute("envites", envites);
//
//				rd = request.getRequestDispatcher("/ventanaJuego.jsp");
//			}
//
			
//			response.sendRedirect("main.jsp");			
			session.setAttribute("user", jg);
			session.setAttribute("userId", jg.getApodo());
//			response.sendRedirect("main.jsp");

			RequestDispatcher rd= null;
			request.setAttribute("jugador", jg);
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

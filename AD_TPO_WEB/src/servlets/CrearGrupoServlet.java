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
import javax.servlet.http.HttpSession;

import dtos.GrupoDTO;
import dtos.JugadorDTO;
import dtos.MiembroGrupoDTO;
import businessDelegate.BusinessDelegate;



@WebServlet("/crearGrupoServlet")
public class CrearGrupoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
	private static BusinessDelegate bd;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearGrupoServlet() {
	        super();
    }

    
    @Override
    public void init() throws ServletException {
    	super.init();
    	try {
			bd = new BusinessDelegate();
		} catch (RemoteException e) {
		    throw new ServletException(e);
		}
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		// seteamos el tiempo maximo de vida de la session (tambien se puede hacer en el descriptor de la aplicacion -web.xml-)
		// session.setMaxInactiveInterval(20*60);
		// JugadorDTO jugador = (JugadorDTO) request.getAttribute("jugador");
		
		String nombre = request.getParameter("nombreGrupo");
		String apodo = request.getParameter("apodoJugador");
		int idJugador = Integer.valueOf(request.getParameter("idJugador")).intValue();
		JugadorDTO jugador = new JugadorDTO();
		jugador.setApodo(apodo);
		jugador.setId(idJugador);

		// finalizamos voluntariamente una sesion!
		// session.invalidate();
		
		try {
			GrupoDTO dto = new GrupoDTO();
			dto.setNombre(nombre);
			
			bd.crearGrupo(dto, jugador);
			
			RequestDispatcher rd = null;
			List<MiembroGrupoDTO> miembrosMandar = bd.obtenerMiembrosGrupo(dto);
			request.setAttribute("jugador", jugador);
			request.setAttribute("grupo", dto);
			
			request.setAttribute("miembrosGrupo", miembrosMandar);
			
			rd = getServletContext().getRequestDispatcher("/ventanaGrupo.jsp");
			rd.forward(request, response);
			
			
		} catch (Exception e) {
			session.setAttribute("error", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/crearGrupo.jsp");
			rd.forward(request, response);
		}
		

	}
	
}
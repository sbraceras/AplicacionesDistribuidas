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
import dtos.MiembroGrupoDTO;

/**
 * Servlet implementation class SeleccionarJugadoresCerradoServlet
 */
@WebServlet("/SeleccionarJugadoresCerrado")
public class SeleccionarJugadoresCerradoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static BusinessDelegate bd;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeleccionarJugadoresCerradoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		int idGrupo = Integer.valueOf(request.getParameter("idGrupo")).intValue();
		String nombreGrupo = request.getParameter("nombreGrupo");
		
		JugadorDTO jugador = new JugadorDTO();
		jugador.setApodo(apodoJugador);
		jugador.setId(idJugador);
		
		GrupoDTO grupoSeleccionado = new GrupoDTO();
		grupoSeleccionado.setNombre(nombreGrupo);
		
		List<MiembroGrupoDTO> miembros = new ArrayList<MiembroGrupoDTO>();
		
		RequestDispatcher rd = null;
		try {
						
			//Busco al jugador con toda la informacion que lleva consigo//
			jugador= bd.obtenerJugadorCompleto(jugador);
			
			//Traigo la lista de miembros del grupo que seleccione anteriormente//
			miembros = bd.obtenerMiembrosGrupo(grupoSeleccionado);	
			
			request.setAttribute("grupo", grupoSeleccionado);
			request.setAttribute("miembrosGrupo", miembros);
			request.setAttribute("jugador", jugador);
			
			rd = getServletContext().getRequestDispatcher("/CrearPartidoGrupo.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {

			rd = request.getRequestDispatcher("/main.jsp");
			rd.forward(request, response);
		}
		
	}
}

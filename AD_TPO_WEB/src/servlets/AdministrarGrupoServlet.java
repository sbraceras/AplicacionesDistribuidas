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

import dtos.GrupoDTO;
import dtos.JugadorDTO;
import dtos.MiembroGrupoDTO;
import businessDelegate.BusinessDelegate;

 
@WebServlet ("/AdministrarGrupo")
public class AdministrarGrupoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static BusinessDelegate bd;

	
	public AdministrarGrupoServlet() {
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
		
		GrupoDTO grupo;
		JugadorDTO administrador;
		int idJugador = Integer.valueOf(request.getParameter("idAdministrador")).intValue();
		String apodoJugador = request.getParameter("apodoAdministrador");
		String apodoMiembro = request.getParameter("apodoMiembro");
		String nombreGrupo = request.getParameter("nombreGrupo");
		administrador = new JugadorDTO();
		JugadorDTO miembro = new JugadorDTO();
		grupo = new GrupoDTO();
		
		if(request.getParameter("action")==null)
		{
			//hay que agregar un miembro es por post 
			
			administrador.setId(idJugador);
			administrador.setApodo(apodoJugador);
			grupo.setNombre(nombreGrupo);
			List<JugadorDTO> miembros = new ArrayList<JugadorDTO>();
			miembro = new JugadorDTO();
			miembro.setApodo(apodoMiembro);
			miembros.add(miembro);
			
			try {
				bd.agregarJugadorGrupo(miembros, grupo, administrador);
			} catch (RemoteException e) {
				
				e.printStackTrace();
			}
			
			
		}
		else
		{
			//hay que eliminar un miembro es por parametros
			
			
			grupo.setNombre(nombreGrupo);
			administrador.setApodo(apodoJugador);
			administrador.setId(idJugador);
			miembro.setApodo(apodoMiembro);
			
			try {
				bd.eliminarMiembroGrupo(miembro, grupo, administrador);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
			
		}
		
		try {
			RequestDispatcher rd = null;
			List<MiembroGrupoDTO> miembrosMandar = bd.obtenerMiembrosGrupo(grupo);
			request.setAttribute("jugador", administrador);
			request.setAttribute("grupo", grupo);
			request.setAttribute("miembrosGrupo", miembrosMandar);
			
			rd = getServletContext().getRequestDispatcher("/ventanaGrupo.jsp");
			rd.forward(request, response);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		
	}


	
}

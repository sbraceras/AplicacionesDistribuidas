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
import dtos.ParejaDTO;
import dtos.PartidoDTO;

/**
 * Servlet implementation class CrearPartidaCerradaServlet
 */
@WebServlet("/CrearPartidaCerrada")
public class CrearPartidaCerradaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static BusinessDelegate bd;   
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CrearPartidaCerradaServlet() {
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
		

		String apodoJugador1 = request.getParameter("apodoJugador1");
		int idJugador1 = Integer.valueOf(request.getParameter("idJugador1")).intValue();
		String apodoJugador2 = request.getParameter("apodoJugador2");
		int idJugador2 = Integer.valueOf(request.getParameter("idJugador2")).intValue();
		String apodoJugador3 = request.getParameter("apodoJugador3");
		int idJugador3 = Integer.valueOf(request.getParameter("idJugador3")).intValue();
		String apodoJugador4 = request.getParameter("apodoJugador4");
		int idJugador4 = Integer.valueOf(request.getParameter("idJugador4")).intValue();
		
		JugadorDTO jg1 = new JugadorDTO();
		JugadorDTO jg2 = new JugadorDTO();
		JugadorDTO jg3 = new JugadorDTO();
		JugadorDTO jg4 = new JugadorDTO();
		JugadorDTO admin = (JugadorDTO) request.getAttribute("jugador");

		jg1.setApodo(apodoJugador1);
		jg1.setId(idJugador1);
		jg1.setApodo(apodoJugador2);
		jg1.setId(idJugador2);
		jg1.setApodo(apodoJugador3);
		jg1.setId(idJugador3);
		jg1.setApodo(apodoJugador4);
		jg1.setId(idJugador4);
		
		GrupoDTO grupo = (GrupoDTO) request.getAttribute("grupo");
		
		ParejaDTO pareja1 = new ParejaDTO();
		pareja1.setJugador1(jg1.getApodo());
		pareja1.setJugador2(jg2.getApodo());
		ParejaDTO pareja2 = new ParejaDTO();
		pareja2.setJugador1(jg3.getApodo());
		pareja2.setJugador2(jg4.getApodo());
		
		List<ParejaDTO> parejas = new ArrayList<ParejaDTO>();
		parejas.add(pareja1);
		parejas.add(pareja2);
		
		
		
		

		try {
		
			RequestDispatcher rd;
			
			bd.armarParejaGrupo(pareja1, grupo, admin);
			bd.armarParejaGrupo(pareja2, grupo, admin);
			
			PartidoDTO miPartido = bd.armarPartidoGrupo(parejas, grupo, admin);

			request.setAttribute("jugador", admin);
			
			/*
			 * Falta ver esta parte. La seccion en la que va a buscar ultimopartido la vamos 
			 * a utilizar para la busqueda nada mas, asi que esta para sacarla de aca.
			 */
			/*
			if (miPartido == null) {
				// aun no se armo el partido en esa modalidad
				PartidoDTO ultimoPartido = bd.obtenerUltimoPartidoPendienteModalidad(TipoPartido.LibreIndividual, jg);
				// enviamos el ultimo identificador de partido para que pueda obtener el partido nuevo!
				request.setAttribute("idUltimoPartido", ultimoPartido == null ? 0 : ultimoPartido.getId());
				request.setAttribute("tipoPartido", TipoPartido.LibreIndividual);
				
				rd = request.getRequestDispatcher("/ventanaEsperandoPartido.jsp");
			} else {
				// le pasamos a la pagina todos los parametros de juego que se necesitan
				JugadorDTO jugadorActual = bd.obtenerJugadorActual(miPartido, jg);
				List<CartaJugadorDTO> misCartas = bd.obtenerCartasJugador(miPartido, jg);
				List<PuntajeParejaDTO> puntajes = bd.obtenerPuntajeChico(miPartido, jg);
				List<JugadorDTO> ganadoresBazas = bd.obtenerGanadoresBazas(miPartido, jg);
				ManoDTO ultimaMano = bd.obtenerUltimaManoActiva(miPartido, jg);
				List<MovimientoDTO> movimientos = bd.obtenerMovimientosUltimaBaza(miPartido, jg);

				
				request.setAttribute("miPartido", miPartido);
				request.setAttribute("jugadorActual", jugadorActual);
				request.setAttribute("parejas", miPartido.getParejas());
				request.setAttribute("misCartas", misCartas);
				request.setAttribute("puntajes", puntajes);
				request.setAttribute("estadoPartido", EstadoPartido.Empezado);
				request.setAttribute("bazas", ultimaMano.getBazas());
				request.setAttribute("ganadoresBazas", ganadoresBazas);
				request.setAttribute("movimientos", movimientos);

				List<TipoEnvite> envites = new ArrayList<TipoEnvite>();
				request.setAttribute("envites", envites);

				rd = request.getRequestDispatcher("/ventanaJuego.jsp");
			}
			
			rd.forward(request, response);
			 
			 */
		} catch (Exception e) {

			RequestDispatcher rd = request.getRequestDispatcher("/main.jsp");
			rd.forward(request, response);
		}
		
	}
		


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}

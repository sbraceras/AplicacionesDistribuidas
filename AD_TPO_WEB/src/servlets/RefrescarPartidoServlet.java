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

import businessDelegate.BusinessDelegate;
import dtos.CartaJugadorDTO;
import dtos.JugadorDTO;
import dtos.ManoDTO;
import dtos.ParejaDTO;
import dtos.PartidoDTO;
import dtos.PuntajeParejaDTO;
import enums.EstadoPartido;
import enums.TipoEnvite;


@WebServlet ("/RefrescarPartido")
public class RefrescarPartidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static BusinessDelegate bd;

    public RefrescarPartidoServlet() {
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
		processRequest(request,response);
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}
	
	
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Se llama a esta funcion cada vez que se tiene que refrescar el juego
		
		int idJugador = Integer.valueOf(request.getParameter("idJugador")).intValue();
		String apodoJugador = request.getParameter("apodoJugador");
		
		int idPartido = Integer.valueOf(request.getParameter("idPartido")).intValue();
		
		JugadorDTO jugadorActual;

		
		JugadorDTO jugador = new JugadorDTO();
		jugador.setId(idJugador);
		jugador.setApodo(apodoJugador);
		
		PartidoDTO partido = new PartidoDTO();
		partido.setId(idPartido);

		
		
		try {
			RequestDispatcher rd = null;
			
			jugadorActual = bd.obtenerJugadorActual(partido, jugador);
			List<CartaJugadorDTO> misCartas = bd.obtenerCartasJugador(partido, jugador);
			List<PuntajeParejaDTO> puntajes = bd.obtenerPuntajeChico(partido, jugador);
			List<ParejaDTO> parejas = bd.obtenerParejasPartido(partido);
			ManoDTO ultimaMano = bd.obtenerUltimaManoActiva(partido, jugador);
			List<JugadorDTO> ganadoresBazas = bd.obtenerGanadoresBazas(partido, jugador);
	
			request.setAttribute("miPartido", partido);
			request.setAttribute("jugador", jugador);
			request.setAttribute("jugadorActual", jugadorActual);
			request.setAttribute("parejas", parejas);
			request.setAttribute("misCartas", misCartas);
			request.setAttribute("puntajes", puntajes);
			request.setAttribute("estadoPartido", EstadoPartido.Empezado);
			request.setAttribute("bazas", ultimaMano.getBazas());
			request.setAttribute("ganadoresBazas", ganadoresBazas);

			if(idJugador== jugadorActual.getId()) {
				//Es el Turno de este jugador
				List<TipoEnvite> envites = bd.obtenerEnvitesDisponibles(partido, jugador);
				request.setAttribute("envites", envites);
			}
	
			rd = getServletContext().getRequestDispatcher("/ventanaJuego.jsp");
			rd.forward(request, response);
		
	  } catch (RemoteException e) {
		
		  e.printStackTrace();
	 }
		
	}
}

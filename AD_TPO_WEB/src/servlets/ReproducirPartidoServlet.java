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

import dtos.CartaJugadorDTO;
import dtos.JugadorDTO;
import dtos.MovimientoDTO;
import dtos.PartidoDTO;
import businessDelegate.BusinessDelegate;



@WebServlet ("/ReproducirPartido")
public class ReproducirPartidoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static BusinessDelegate bd;

	
    public ReproducirPartidoServlet() {
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
		
		
		int idJugador = Integer.valueOf(request.getParameter("idJugador")).intValue();
		String apodoJugador = request.getParameter("apodoJugador");
		int idPartido = Integer.valueOf(request.getParameter("idPartido")).intValue();
		int idMovimiento = Integer.valueOf(request.getParameter("ultimoMovimiento")).intValue();
		JugadorDTO jugador = new JugadorDTO();
		PartidoDTO partido = new PartidoDTO();
		List<CartaJugadorDTO> cartasJugador = null;
		jugador.setApodo(apodoJugador);
		jugador.setId(idJugador);
		partido.setId(idPartido);
		List<MovimientoDTO> movimientos = new ArrayList<MovimientoDTO>();
		boolean terminado= false;
		
		RequestDispatcher rd = null;
		
		request.setAttribute("parejas", bd.obtenerParejasPartido(partido));
		
		if(idMovimiento == 0)
		{
			//Recien arranca el partido a reproducir
			
			try {
				movimientos = bd.obtenerProximoMovimientoPartido(jugador, partido, null);
				cartasJugador = bd.obtenerCartasJugadorMano(jugador, partido, null);
				
				
			} catch (RemoteException e) {
				//Transportar bien el error
				////////////////
				//////////
				e.printStackTrace();
			}			
		}
		else
		{
			//Son los siguientes movimientos
						
						
			MovimientoDTO ultimoMovimiento = new MovimientoDTO();
			ultimoMovimiento.setId(idMovimiento);
			
			try {
				movimientos = bd.obtenerProximoMovimientoPartido(jugador, partido, ultimoMovimiento);
				
				
				if(movimientos.size()==0){
				//Significa que ya no hay proximo movimiento, el partido termino
					
					terminado=true;
					request.setAttribute("jugador", jugador);
					request.setAttribute("partido", partido);
					request.setAttribute("terminado", "SI");
					request.setAttribute("parejaGanadora", bd.obtenerParejaGanadoraPartido(jugador, partido));
					request.setAttribute("puntajes", bd.obtenerResultadoFinalPartido(jugador, partido));
					rd = getServletContext().getRequestDispatcher("/VentanaReproducirJuego.jsp");
					rd.forward(request, response);
								
				}
				else
				{
					cartasJugador = bd.obtenerCartasJugadorMano(jugador, partido, movimientos.get(movimientos.size()-1));
				}
				
			} catch (RemoteException e) {
				//Transportar bien el error
				////////////////
				//////////
				e.printStackTrace();
			}
			
						
			
		}
		
	
		if(terminado == false){
		request.setAttribute("terminado", "NO");
		request.setAttribute("jugador", jugador);
		request.setAttribute("movimientos", movimientos);
		request.setAttribute("partido", partido);
		request.setAttribute("cartasJugador", cartasJugador);
		
		
		rd = getServletContext().getRequestDispatcher("/VentanaReproducirJuego.jsp");
		rd.forward(request, response);
		}
		
				
	}
	
	
	

}

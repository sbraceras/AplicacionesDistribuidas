package servlets;

import java.io.IOException;
import java.rmi.RemoteException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessDelegate.BusinessDelegate;

@WebServlet
public class AdministrarGrupoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static BusinessDelegate bd;

	public AdministrarGrupoServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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

	
}

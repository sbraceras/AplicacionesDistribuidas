package daos;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dtos.PartidoDTO;

import bean.Partido;
import hibernate.HibernateUtil;

public class PartidoDAO {

	private static PartidoDAO instancia = null;
	private static SessionFactory sf = null;

	public static PartidoDAO getInstance() {
		if (instancia == null) {
			sf = HibernateUtil.getSessionFactory();
			instancia = new PartidoDAO();
		}
		return instancia;
	}

	public Integer guardarPartido(Partido partido) {
		Transaction t = null;
		Session s = sf.openSession();
		
		try {
			t = s.beginTransaction();
			Integer id = (Integer) s.save(partido);
			s.flush();
			t.commit();
			s.close();

			return id;
		} catch (Exception e) {
			t.rollback();
			s.close();
			e.printStackTrace();
			System.out.println("Error al guardar Partido");
		}
		return null;
	}

	public Partido buscarPartido(PartidoDTO partido) {
		return null;
	}

}

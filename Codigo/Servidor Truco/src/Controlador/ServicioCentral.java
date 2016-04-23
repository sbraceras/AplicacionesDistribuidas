package Controlador;
/**
 * Es el controlador del negocio
**/
public class ServicioCentral {
	private static ServicioCentral instancia;
	private Collection<Jugador> jugadores;
	private Collection<Partido> partidos;
	private Collection<Grupo> grupos;
	private Collection<Jugador> sesiones;
	private Collection<Jugador> esperandoLibreInvidividual;
	private Collection<Parejas> esperandoLibreParejas;
	public static ServicioCentral getInstance() {
	
	}
	
	public void registrarJugador(JugadorDTO jugador) {
	
	}
	
	private Jugador obtenerJugador(JugadorDTO jugador) {
	
	}
	
	public boolean existeGrupo(String nombre) {
	
	}
	
	public void crearGrupo(String nombre, JugadorDTO administrador) {
	
	}
	
	public void agregarJugadorGrupo(Collection<JugadorDTO> jugadores, String grupo, JugadorDTO administrador) {
	
	}
	
	public void armarParejaGrupo(Collection<JugadorDTO> integrantes, String grupo, JugadorDTO administrador) {
	
	}
	
	public void crearPartidaGrupo(Collection<ParejaDTO> parejas, String grupo, JugadorDTO administrador) {
	
	}
	
	public OpcionesDisponiblesDTO iniciarSesion(JugadorDTO jugador) {
	
	}
	
	public void jugarLibreIndividual(JugadorDTO jugador) {
	
	}
	
	private Collection<Pareja> armarParejasInvididual(TipoCategoria categoria) {
	
	}
	
	private Partido armarPartido(Collection<Pareja> parejas, String tipoPartido) {
	
	}
	
	private Pareja obtenerParejaEnemiga(TipoCategoria categoria) {
	
	}
	
	public void jugarLibreParejas(ParejaDTO pareja) {
	
	}
	
	public void eliminarMiembroGrupo(JugadorDTO jugador, String grupo, JugadorDTO administrador) {
	
	}
	
	public void iniciarSesion(JugadorDTO jugador) {
	
	}
	
	public void cerrarSesion(JugadorDTO jugador) {
	
	}
	
	public Collection<JugadorDTO> obtenerJugadores() {
	
	}
	
	public Collection<PartidoDTO> obtenerPartidos(timeStamp fechaDesde, timeStamp fechaHasta, TipoPartido modalidad, JugadorDTO jugador) {
	
	}
	
	public PartidoDTO obtenerPartidoJugado(PartidoDTO partido) {
	
	}
	
	public ChicoDTO obtenerChicoDTO(partidoDTO partido, int chico) {
	
	}
	
	public Collection<RankingDTO> obtenerRankingGeneral(JugadorDTO jugador) {
	
	}
	
	public Collection<RankingDTO> obtenerRankingGrupo(GrupoDTO grupo, JugadorDTO jugador) {
	
	}
	
	public Pareja armarPareja(Collection<Jugador jugadores) {
	
	}
	
	public Grupo obtenerGrupo(String nombre) {
	
	}
}

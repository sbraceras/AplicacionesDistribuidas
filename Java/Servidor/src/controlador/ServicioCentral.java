package controlador;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.*;
import daos.*;
import dtos.*;
import enums.*;
import exceptions.*;

/**
 * Es el controlador del negocio
 **/
public class ServicioCentral {

	private static ServicioCentral controlador;

	private ArrayList<Jugador> jugadores;
	private ArrayList<Partido> partidos;
	private ArrayList<Grupo> grupos;
	private ArrayList<Jugador> sesiones;
	private ArrayList<Jugador> esperandoLibreIndividual;
	private ArrayList<Pareja> esperandoLibreParejas;

	private ServicioCentral() {
		this.jugadores = new ArrayList<Jugador>();
		this.partidos = new ArrayList<Partido>();
		this.grupos = new ArrayList<Grupo>();
		this.sesiones = new ArrayList<Jugador>();
		this.esperandoLibreIndividual = new ArrayList<Jugador>();
		this.esperandoLibreParejas = new ArrayList<Pareja>();

		try {
			// si se reinicia el Servidor, o se corta la luz, debo levantar los partidos pendientes!
			levantarPartidosPendientes();
		} catch (PartidoException e) {
			e.printStackTrace();
		}
	}

	public static ServicioCentral getInstance() {
		if (controlador == null) {
			controlador = new ServicioCentral();
		}
		return controlador;
	}

	public void registrarJugador(JugadorDTO jugador) throws JugadorException {
		Jugador jug = obtenerJugadorPorApodoMail(jugador);

		if (jug != null) {
			if (jug.getApodo().equalsIgnoreCase(jugador.getApodo()))
				throw new JugadorException("El apodo ingresado ya est� en uso");
			if (jug.getMail().equalsIgnoreCase(jugador.getMail()))
				throw new JugadorException("El correo electr�nico ingresado ya esta en uso");
		} else {
			// Podemos registrar el Jugador! 
			// Suponemos que la validacion de la segunda password la hace la interfaz
			jug = new Jugador(jugador.getApodo(), jugador.getMail(), jugador.getPassword());

			JugadorDAO.getinstance().guardarJugador(jug);
			jugadores.add(jug);
		}
	}

	private Jugador obtenerJugadorPorApodoPassword(JugadorDTO jugador) {
		for (Jugador jug: jugadores) {
			if (jug.getApodo().equalsIgnoreCase(jugador.getApodo()) &&
				// La Password la hacemos sensible a May�sculas
				jug.getPassword().equals(jugador.getPassword())) {
					return jug;
			}
		}

		// no lo encontro en memoria, lo busco en la BD
		Jugador jug = JugadorDAO.getinstance().buscarJugadorPorApodoPassword(jugador);

		if (jug != null)
			jugadores.add(jug); // lo subo a memoria

		return jug;
	}

	private Jugador obtenerJugadorPorApodoMail(JugadorDTO jugador) {
		for (Jugador jug: jugadores) {
			if (jug.getMail().equalsIgnoreCase(jugador.getMail()) ||
				jug.getApodo().equalsIgnoreCase(jugador.getApodo())) {
				return jug;
			}
		}

		// no lo encontro en memoria, lo busco en la BD
		Jugador jug = JugadorDAO.getinstance().buscarJugadorPorApodoMail(jugador);

		if (jug != null)
			jugadores.add(jug); // lo agrego a memoria

		return jug;
	}

	private Jugador obtenerJugador(JugadorDTO jugador) {
		for (int i = 0; i < jugadores.size(); i++) {
			if (jugadores.get(i).sosJugador(jugador))
				return jugadores. get(i);
		}

		// no lo encontro en memoria, lo busco en la BD
		Jugador jug = JugadorDAO.getinstance().buscarJugador(jugador);

		if (jug != null)
			jugadores.add(jug); // lo agrego a memoria

		return jug;
	}

	public boolean existeGrupo(GrupoDTO dto) {
		if (obtenerGrupo(dto) != null)
			return true;
		return false;
	}

	public RankingDTO obtenerRankingGeneral(JugadorDTO jugador,
			ArrayList<RankingDTO> rank) {
		Jugador jug = obtenerJugador(jugador);
		if (jug != null) {
			RankingDTO devolver = new RankingDTO();
			devolver = obtenerRanking(jug);
			return devolver;
		}
		return null;
	}

	public RankingDTO obtenerRanking(Jugador jug) {
		return jug.getRanking().toDTO();
	}

	public void crearGrupo(GrupoDTO dto, JugadorDTO administrador) {
		if (!existeGrupo(dto)) {
			Jugador jug = obtenerJugador(administrador);
			if (jug != null) {
				Grupo grupo = new Grupo(dto.getNombre(), jug);
				jug.agregarGrupo(grupo);
//				GrupoDAO.getInstancia().guardarGrupo(grupo);    HACE FALTA ??? EL GRUPO SE ESTARIA GUARDANDO CON EL JUGADOR!
				grupos.add(grupo);
			}
		}
	}

	public void agregarJugadorGrupo(List<JugadorDTO> agregar, GrupoDTO dto, JugadorDTO administrador) {
		Grupo grupo = obtenerGrupo(dto);

		if (grupo != null) {

			Jugador jugador = obtenerJugador(administrador);

			if (jugador != null) {
				if (grupo.esAdministrador(jugador)) {

					Jugador jug2;

					for (int i = 0; i < agregar.size(); i++) {

						jug2 = obtenerJugador(agregar.get(i));
						if (jug2 != null) {
							grupo.agregarMiembro(jug2);
							jug2.agregarGrupo(grupo);
						}

					}

				}
			}

		}

	}


	public void armarParejaGrupo(ArrayList<JugadorDTO> integrantes,
			GrupoDTO dto, JugadorDTO administrador) {

		Grupo grupo = obtenerGrupo(dto);

		if (grupo != null) {

			Jugador jugador = obtenerJugador(administrador);

			if (jugador != null) {

				if (grupo.esAdministrador(jugador)) {

					ArrayList<Jugador> parejas = new ArrayList<Jugador>();

					for (int i = 0; i < integrantes.size(); i++) {

						parejas.add(obtenerJugador(integrantes.get(i)));
					}

					grupo.armarPareja(parejas);

				}

			}

		}

	}

	/* HACER SEGUN DIAGRAMA DE SECUENCIAS */
	public PartidoDTO crearPartidaGrupo(ArrayList<ParejaDTO> parejas, GrupoDTO dto,
			JugadorDTO administrador) {
		Grupo grupo = obtenerGrupo(dto);

		if (grupo != null) {

			Jugador jugador = obtenerJugador(administrador);

			if (jugador != null) {
				
				if (grupo.esAdministrador(jugador)) {
					ArrayList<Pareja> ingresan = new ArrayList<Pareja>();

					for (int i = 0; i < parejas.size(); i++) {

						if (grupo.tenesPareja(parejas.get(i)))
							ingresan.add(grupo.obtenerPareja(parejas.get(i)));
					}

					if (ingresan.size() == 2) /* tengo ambas parejas activas */
					{
						Date date = new Date();
						Partido partido = new Partido(ingresan,
								(Timestamp) date, TipoPartido.Grupo);
						grupo.agregarPartido(partido);
						partidos.add(partido);
						
						int idPartido = PartidoDAO.getInstance().guardarPartido(partido).intValue();
						partido.setId(idPartido);
						
						return partido.toDTO();
					}
				}
			}
		}
		return null;
	}

	public PartidoDTO armarPartidoIndividual(){
		
		if (esperandoLibreIndividual.size()>=4){
			List<Jugador> jugadoresPosibles = new ArrayList<Jugador>();
			Partido partido = null;
			String categoriaMedia = esperandoLibreIndividual.get(esperandoLibreIndividual.size()-1).getCategoria().toString();
			jugadoresPosibles.add(esperandoLibreIndividual.get(esperandoLibreIndividual.size()-1));
			for (Jugador j : esperandoLibreIndividual){
				if (!jugadoresPosibles.contains(j) && j.getCategoria().toString().equalsIgnoreCase(categoriaMedia) && jugadoresPosibles.size()<4){
					//Encontre un jugador distinto de la misma categoría. No los saco de esperandoLibreIndividual hasta terminar el metodo.//
					jugadoresPosibles.add(j);
				}
							
			}
			
			//Si llegue a armar con la misma categoria//
			if (jugadoresPosibles.size()==4){
				Pareja pareja1 = new Pareja(jugadoresPosibles.get(0), jugadoresPosibles.get(1));
				Pareja pareja2 = new Pareja(jugadoresPosibles.get(2), jugadoresPosibles.get(3));
				List<Pareja> parejas = new ArrayList<Pareja>();
				parejas.add(pareja1);
				parejas.add(pareja2);
				
				esperandoLibreIndividual.remove(jugadoresPosibles.get(0));
				esperandoLibreIndividual.remove(jugadoresPosibles.get(1));
				esperandoLibreIndividual.remove(jugadoresPosibles.get(2));
				esperandoLibreIndividual.remove(jugadoresPosibles.get(3));
				
				partido = new Partido(parejas, new Timestamp(System.currentTimeMillis()), TipoPartido.LibreIndividual);
				partidos.add(partido);
				
				partido.setId(PartidoDAO.getInstance().guardarPartido(partido).intValue());
				return partido.toDTO();
			}
			
			//Si no llegue a armar con la misma categoría//
			if (jugadoresPosibles.size()<4){
                //Defino mayor y menor//
				String categoriaMayor = TipoCategoria.obtenerTipoMayor(categoriaMedia);
				String categoriaMenor = TipoCategoria.obtenerTipoMenor(categoriaMedia);
							
				
				//Busco en la mayor, solamente si existe una categoría mayor (si es experto no puedo buscar en una mayor).//
				if (categoriaMayor != null){
					for (Jugador j : esperandoLibreIndividual){
						if (!jugadoresPosibles.contains(j) && j.getCategoria().toString().equalsIgnoreCase(categoriaMayor) && jugadoresPosibles.size()<4){
							//Encontre un jugador distinto en la categoria mayor. No los saco de esperandoLibreIndividual hasta terminar el metodo.//
							jugadoresPosibles.add(j);
						}
					}				
				}
				
				//Si todavia no llego a los jugadores, voy a buscar en la menor. Para eso saco los elementos con categoria mayor de la lista de jugadoresPosibles.//
				
				List<Jugador> jugadoresBorrar = new ArrayList<Jugador>();
				if (jugadoresPosibles.size()<4){
					for (Jugador j : jugadoresPosibles){
						if (j.getCategoria().toString().equalsIgnoreCase(categoriaMayor)){
//							jugadoresPosibles.remove(j);
							jugadoresBorrar.add(j);
						}
					}
					if(jugadoresBorrar.size()>0)
						jugadoresPosibles.remove(jugadoresBorrar);
					
				}else{
					//Si llegué con categoria original y mayor, compongo las parejas//
					Pareja pareja1 = new Pareja(jugadoresPosibles.get(0), jugadoresPosibles.get(1));
					Pareja pareja2 = new Pareja(jugadoresPosibles.get(2), jugadoresPosibles.get(3));
					List<Pareja> parejas = new ArrayList<Pareja>();
					parejas.add(pareja1);
					parejas.add(pareja2);

					//invocar a armarPartido, solamente si tengo al menos 2 de la categoria original//
					if (parejasCompatibles(jugadoresPosibles, categoriaMedia)){
						partido = new Partido(parejas, new Timestamp(System.currentTimeMillis()), TipoPartido.LibreIndividual);
						partidos.add(partido);

						esperandoLibreIndividual.remove(jugadoresPosibles.get(0));
						esperandoLibreIndividual.remove(jugadoresPosibles.get(1));
						esperandoLibreIndividual.remove(jugadoresPosibles.get(2));
						esperandoLibreIndividual.remove(jugadoresPosibles.get(3));

						partido.setId(PartidoDAO.getInstance().guardarPartido(partido).intValue());
						return partido.toDTO();
					}
				}
				
				//Voy a buscar en la menor, solamente si existe//
				if (categoriaMenor != null){
					for (Jugador j : esperandoLibreIndividual){
						if (!jugadoresPosibles.contains(j) && j.getCategoria().toString().equalsIgnoreCase(categoriaMenor)){
							jugadoresPosibles.add(j);
						}
					}
				}
				
				//Si encontre 4 jugadores entre categoria original y menor//
				if (jugadoresPosibles.size()==4){
					//componer parejas//
					Pareja pareja1 = new Pareja(jugadoresPosibles.get(0), jugadoresPosibles.get(1));
					Pareja pareja2 = new Pareja(jugadoresPosibles.get(2), jugadoresPosibles.get(3));
					List<Pareja> parejas = new ArrayList<Pareja>();
					parejas.add(pareja1);
					parejas.add(pareja2);

					//invocar a armarPartido//
					if (parejasCompatibles(jugadoresPosibles, categoriaMenor)){
						
						partido = new Partido(parejas, new Timestamp(System.currentTimeMillis()), TipoPartido.LibreIndividual);
						partidos.add(partido);

						esperandoLibreIndividual.remove(jugadoresPosibles.get(0));
						esperandoLibreIndividual.remove(jugadoresPosibles.get(1));
						esperandoLibreIndividual.remove(jugadoresPosibles.get(2));
						esperandoLibreIndividual.remove(jugadoresPosibles.get(3));

						partido.setId(PartidoDAO.getInstance().guardarPartido(partido).intValue());
						return partido.toDTO();
					}
				}
			}
						
		}
		
		return null;
		
	}
	
	public PartidoDTO armarPartidoParejas(){
		
		List<Pareja> parejasPosibles = new ArrayList<Pareja>();
		Partido partido = null;
		parejasPosibles.add(esperandoLibreParejas.get(esperandoLibreParejas.size()-1));
		TipoCategoria categoriaSuperior = parejasPosibles.get(0).obtenerCategoriaSuperior();
		
		for (Pareja p : esperandoLibreParejas){
			if (p.obtenerCategoriaSuperior().equals(categoriaSuperior)){
				//Encontre pareja, armo el partido//
				parejasPosibles.add(p);
				partido = new Partido(parejasPosibles, new Timestamp(System.currentTimeMillis()), TipoPartido.LibreParejas);
				
				partido.setId(PartidoDAO.getInstance().guardarPartido(partido).intValue());
				return partido.toDTO();
			}
		}
				
		return null;
	
	}

	public List<PartidoDTO> tengoPartido(JugadorDTO jugadorDTO){
		Jugador jug = obtenerJugador(jugadorDTO);

		List<PartidoDTO> partidosActivos = new ArrayList<PartidoDTO>();
		for (Partido p : partidos) {
			if (p.participoJugador(jug) && !p.estasTerminado()) {
				PartidoDTO partidoDTO = p.toDTO();
				partidosActivos.add(partidoDTO);				
			}
		}

		/* No buscamos en la BD los partidos porque ya estan levantados los pendientes en memoria!  */

		return partidosActivos;
	}
	

	public PartidoDTO jugarLibreIndividual(JugadorDTO jugador){
		for(Jugador aux: sesiones)
		{
			if(aux.sosJugador(jugador)) {
				esperandoLibreIndividual.add(aux);
				return armarPartidoIndividual();
			}
		}
		return null;
	}
	
	
	public void eliminarMiembroGrupo(JugadorDTO jugador, GrupoDTO grupo,
			JugadorDTO administrador) {

		Jugador aux = obtenerJugador(jugador);
		Grupo grup;
		Jugador administradorReal;
		if (aux != null) {
			grup = obtenerGrupo(grupo);
			if (grup != null) {
				administradorReal = obtenerJugador(administrador);
				if (grup.esAdministrador(administradorReal)) {
					grup.eliminarMiembroGrupo(aux);
				}
			}
		}
	}


	private boolean parejasCompatibles(List<Jugador> jugadoresPosibles, String categoriaMenor) {
		int cantMenor = 0;
		for (Jugador j : jugadoresPosibles){
			if (j.getCategoria().toString().equalsIgnoreCase(categoriaMenor)){
				cantMenor++;
			}	
		}
		
		if (cantMenor >= 2)
			return true;
		return false;
	}
	
	
	/* 
	 * El jugador ingresa su apodo y su password, si es un jugador registrado 
	 * y su password concuerda con la almacenada se le permite pasar al area 
	 * de juego para elegir el tipo de partida a jugar.
	 */
	public JugadorDTO iniciarSesion(JugadorDTO jugador) throws JugadorException {
	
//		for(Jugador jug: sesiones) {
//			if (jug.getApodo().equals(jugador.getApodo()))
//				throw new JugadorException("Ya ha iniciado sesion");
//		}

		Jugador jug = obtenerJugadorPorApodoPassword(jugador);

		if (jug == null) {
			throw new JugadorException("Inicio de sesion no valido. " +
				"Por favor, verifique sus credenciales.");
		} else {
			System.out.println("LogIn Correcto");
			sesiones.add(jug);

			return jug.toDTO();
		}
	}

	public void cerrarSesion(JugadorDTO jugador) {

		for (int i = 0; i < sesiones.size(); i++) {

			if (sesiones.get(i).sosJugador(jugador))
				sesiones.remove(i);
		}

	}

	/* DESARROLLAR CON HQL */
	public ArrayList<JugadorDTO> obtenerJugadores() {

		ArrayList<Jugador> jug = JugadorDAO.getinstance().obtenerJugadores();

		ArrayList<JugadorDTO> devolver = new ArrayList<JugadorDTO>();

		for (int i = 0; i < jug.size(); i++) {

			devolver.add(jug.get(i).toDTO());
		}
		return devolver;
	}
	
	/* DESARROLLAR CON HQL */
	public List<PartidoDTO> obtenerPartidosJugador(JugadorDTO jugador){
		return null;
	}

	/* DESARROLLAR */
	public ArrayList<PartidoDTO> obtenerPartidosEntreFechas(Timestamp fechaDesde,
			Timestamp fechaHasta, TipoPartido modalidad, JugadorDTO jugador) throws PartidoException {

		/* No se agregan a Memoria ya que solo son para mostrar a la vista */
		
		List<Partido> partidosFechas = PartidoDAO.getInstance().obtenerPartidosEntreFechas(fechaDesde, fechaHasta, modalidad, jugador);
		if(partidosFechas != null)
		{
			ArrayList<PartidoDTO> devolver = new ArrayList<PartidoDTO>();
			for(Partido partido: partidosFechas){
				devolver.add(partido.toDTO());
			}
			return devolver;
		}
		return null;
		
	}

	public ChicoDTO obtenerChicoDTO(PartidoDTO partido, int chico) throws PartidoException {
		Partido p = obtenerPartido(partido);

		if (p != null)
			return p.obtenerChicoActivo().toDto();

		return null;
	}

	/* DESARROLLAR */
	public ArrayList<RankingDTO> obtenerRankingGeneral(JugadorDTO jugador) {

		return null;
	}

	public ArrayList<RankingDTO> obtenerRankingGrupo(GrupoDTO grupo,
			JugadorDTO jugador) {
		Grupo grup = obtenerGrupo(grupo);
		if (grup != null) {

			return grup.obtenerRanking();
		}

		return null;

	}

	/* DESARROLLAR */
	public Pareja armarPareja(ArrayList<Jugador> jugadores) {
		return null;
	}

	public Grupo obtenerGrupo(GrupoDTO dto) {
		for (int i = 0; i < grupos.size(); i++) {
			if (grupos.get(i).getNombre().equals(dto.getNombre()))
				return grupos.get(i);
		}

		/* No lo encontro en memoria */

		Grupo grupo = GrupoDAO.getInstancia().buscarGrupoPorNombre(dto);

		if (grupo != null) {
			grupos.add(grupo); /* lo agrego a memoria */
		}

		return grupo;
	}

	public ArrayList<GrupoDTO> obtenerGrupos() {
		List<Grupo> grup = GrupoDAO.getInstancia().obtenerGrupos();

		ArrayList<GrupoDTO> devolver = new ArrayList<GrupoDTO>();

		for (int i = 0; i < grup.size(); i++) {
			devolver.add(grup.get(i).toDto());
		}

		return devolver;
	}

	private boolean estaLogueado(JugadorDTO jugador) {
		for (Jugador jug: sesiones) {
			if (jug.sosJugador(jugador))
				return true;
		}
		return false;		
	}

	public List<TipoEnvite> obtenerEnvitesDisponibles(PartidoDTO partido, JugadorDTO jugador) throws ControladorException, PartidoException {
		Partido part = obtenerPartido(partido);

		if (part != null) {
			if (estaLogueado(jugador)) {
				// obtengo la mano activa
				Mano mano = part.obtenerChicoActivo().obtenerUltimaMano();
				// verifico si el jugador que le toca jugar es el que envio la peticion!
				if (mano.getJugadorActual().sosJugador(jugador)) {
					return part.obtenerEnvitesPosibles();
				} else
					throw new ControladorException("No le toca jugar a dicho jugador");
			} else
				throw new ControladorException("El jugador no ha iniciado sesion");
		} else
			throw new ControladorException("No existe el partido");
	}
	
	public Partido obtenerPartido(PartidoDTO partido) throws PartidoException {
		for(Partido p: partidos) {
			if(p.sosPartido(partido))
				return p;
		}

		// no lo encontro en memoria, lo busco en la BD
		Partido p = PartidoDAO.getInstance().buscarPartido(partido);

		if (p != null)
			partidos.add(p);

		return p;
	}

	private Movimiento crearMovimientoFromDTO(Jugador jugador, MovimientoDTO movimiento) {
		if (movimiento instanceof CartaTiradaDTO) {
			Carta carta = new Carta();
			carta.setId(((CartaTiradaDTO) movimiento).getCartaJugador().getCarta().getId());
			carta.setPalo(((CartaTiradaDTO) movimiento).getCartaJugador().getCarta().getPalo());
			carta.setNumero(((CartaTiradaDTO) movimiento).getCartaJugador().getCarta().getNumero());
			carta.setPosicionValor(((CartaTiradaDTO) movimiento).getCartaJugador().getCarta().getPosicionValor());

			CartaJugador cartaJugador = new CartaJugador(jugador, carta, true);

			CartaTirada cartaTirada = new CartaTirada(cartaJugador);
			cartaTirada.setFechaHora(movimiento.getFechaHora());

			return cartaTirada;
		} else if (movimiento instanceof EnviteDTO) {
			Envite envite = new Envite(((EnviteDTO) movimiento).getTipoEnvite());
			envite.setJugador(jugador);
			envite.setFechaHora(movimiento.getFechaHora());

			return envite;
		}

		return null;
	}

	public void nuevoMovimientoPartido(PartidoDTO partido, JugadorDTO jugador, MovimientoDTO movimiento) throws ControladorException, PartidoException, BazaException {
		if (estaLogueado(jugador)) {
			Partido part = obtenerPartido(partido);
			if (part == null)
				throw new ControladorException("No existe el partido");

			// fabrico los objetos a partir de los DTOs
			Jugador jug = obtenerJugador(jugador);
			Movimiento mov = crearMovimientoFromDTO(jug, movimiento);
			
			part.nuevoMovimiento(jug, mov);
						
		} else {
			throw new ControladorException("El jugador no ha iniciado sesion");
		}
	}

	public JugadorDTO obtenerJugadorActual(PartidoDTO partido, JugadorDTO jugador) throws ControladorException, PartidoException {
		
		if(estaLogueado(jugador)){
			
			Partido part = obtenerPartido(partido);
		
			if (part == null)
				throw new ControladorException("No existe el partido");
			
			return part.obtenerChicoActivo().obtenerTurnoJugador().toDTO();
						
		} else {
			throw new ControladorException("El jugador no ha iniciado sesion");
		}
	}

		
	public List<CartaJugadorDTO> obtenerCartasJugador (PartidoDTO partido, JugadorDTO jugador) throws ControladorException, PartidoException{
		
		if (estaLogueado(jugador)) {
			Partido part = obtenerPartido(partido);
			if (part == null)
				throw new ControladorException("No existe el partido");

			return part.obtenerChicoActivo().obtenerUltimaMano().obtenerCartasJugador(jugador);

			
		} else {
			throw new ControladorException("El jugador no ha iniciado sesion");
		}
	}
	
	public void levantarPartidosPendientes () throws PartidoException{
		
		partidos.addAll(PartidoDAO.getInstance().obtenerPartidosPendientes());
		
		for(Partido partido: partidos) {
			// levanto toda la informacion que no esta persistida!
			partido.levantar();
		}
		
		System.out.println("Termine de Levantar Todos Los Partidos");
		System.out.println("Levante: " + partidos.size() + " Partidos");
	}
	

}

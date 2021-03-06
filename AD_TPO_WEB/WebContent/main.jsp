<%@page import="javax.xml.bind.ParseConversionEvent"%>
<%@page import="dtos.JugadorDTO"%>
<%@page import="dtos.GrupoDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link type="text/css" rel="stylesheet" href="css/styles.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
	
<link rel="icon" href="images/dice.png">
	
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Truco Web - Aplicaciones Distribuidas</title>

<%
	JugadorDTO jugador = (JugadorDTO) request.getAttribute("jugador");

// 	JugadorDTO jugador = new JugadorDTO();
	
// 	jugador.setApodo(apodoJugador);
// 	jugador.setId(idJugador);
	
// 	request.setAttribute("jugador", jugador);

%>


<script type="text/javascript">

</script>
</head>
<body>
<h1>Truco Web - Aplicaciones Distribuidas - 2016</h1>
<div class="superior">
</div>
<br>
<div class="menu">
<div class="verticalIzquierdo">
<input type="submit" class="boton" value="Buscar Partido" onclick="location.href='buscarpartido.jsp?idJugador=<%=jugador.getId()%>&apodoJugador=<%=jugador.getApodo()%>'"/>
</div> 
<div class="verticalCentro">
<input type="submit" class="boton" value="Crear Grupo" onclick="location.href='crearGrupo.jsp?idJugador=<%=jugador.getId()%>&apodoJugador=<%=jugador.getApodo()%>'"/>
</div>
<div class="abajoCentro">
<input type="submit" class="boton" value="Cerrar Sesion" onclick="location.href='LogoutServlet?idJugador=<%=jugador.getId()%>&apodoJugador=<%=jugador.getApodo()%>'"/>


<div class="botonRanking">
<input type="submit" class="verRanking" value="Ver Ranking" onclick="location.href='VisualizarRanking?idJugador=<%=jugador.getId()%>&apodoJugador=<%=jugador.getApodo()%>'"/>
</div>

<div class="verticalDerecho">
<input type="submit" class="boton" value="Crear Partidos Grupo" onclick="location.href='SeleccionarGrupo?idJugador=<%=jugador.getId()%>&apodoJugador=<%=jugador.getApodo()%>'"/>
</div>

</div>
</div>
    
</body>
</html>
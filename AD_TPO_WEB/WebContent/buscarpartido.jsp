<%@page import="dtos.JugadorDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link type="text/css" rel="stylesheet" href="css/styles.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
	
<%

	String apodoJugador = request.getParameter("apodoJugador");
	int idJugador = Integer.valueOf(request.getParameter("idJugador")).intValue();
	
	JugadorDTO jugador = new JugadorDTO();
	
	jugador.setApodo(apodoJugador);
	jugador.setId(idJugador);
	
// 	request.setAttribute("jugador", jugador);

%>
	
<link rel="icon" href="images/dice.png">
	
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Truco Web - Aplicaciones Distribuidas</title>

</head>
<body>
<h1>Truco Web - Aplicaciones Distribuidas - 2016</h1>
<div class="superior">
</div>
<div class="menu">
<div class="verticalIzquierdo">

<!-- <form id="fPartidaIndividualLibre" action="CrearPartidaIndividualServlet" method=post> -->

<input type="image" src="images/iconoIndividual.png" onclick="location.href='CrearPartidaIndividualServlet?idJugador=<%=jugador.getId()%>&apodoJugador=<%=jugador.getApodo()%>'"/>
<p class="textoBoton">Libre Individual</p>

<!-- </form> -->

</div> 
<div class="verticalCentro">
<input type="image" src="images/iconoPartidaParejas.png" onclick="location.href='seleccionarPareja.jsp?idJugador=<%=jugador.getId()%>&apodoJugador=<%=jugador.getApodo()%>'"/>
<p class="textoBoton">Libre Parejas</p>
</div>
<div class="verticalDerecho">
<input type="image" src="images/iconoPartidaGrupo.png" onclick="location.href='buscarPartidaGrupo.jsp'"/>
<p class="textoBoton">Cerrada</p>
</div>
<div class="abajoCentro">   
<input class="botonVolver" type="submit" value="Volver al Menu" onclick="location.href='VolverAlMenu?idJugador=<%=jugador.getId()%>&apodoJugador=<%=jugador.getApodo()%>'"/>
</div>
</div>
</body>
</html>
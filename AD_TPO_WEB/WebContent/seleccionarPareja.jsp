<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.List"%>
    <%@page import="java.util.ArrayList"%>
    <%@ page import= "dtos.JugadorDTO"%>
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

</head>
<body>
<h1>Truco Web - Aplicaciones Distribuidas - 2016</h1>
<div class="superior">
</div>

<div class="verticalCentro">
<%
List<JugadorDTO> jugadoresDisponibles = (List<JugadorDTO>)request.getAttribute("jugadoresDisponibles");
%>
<form id="seleccionJugadoresForm" method="post" action="CrearPartidaParejaServlet" onsubmit="return validarForm();" >
	<table border="1" name="tblJugadores" >
		<thead>
			<tr>
				<th>Apodo</th>
				<th>Categoria</th>
				<th>Seleccion</th>
			</tr>
		</thead>

<%
if (jugadoresDisponibles != null) {
for (JugadorDTO jugador : jugadoresDisponibles) {
%>
		<tr>
			<td><%=jugador.getApodo()%></td>
			<td><%=jugador.getCategoria().toString() %></td>
			<td>
				<input type="checkbox" name=<%= jugador.getApodo() + "Field" %> />
			</td>
		</tr>
<%
}
} else {
%>
		<tr>
			<td colspan="3">No hay jugadores online</td>
		</tr>
<%
}
%>
	<!-- TODO agregar botones para aceptar, seleccionar todo, desmarcar todo, etc -->
		<tr>
			<td colspan="2"><input type="submit" value="Aceptar seleccion" /></td>
			<td colspan="1"><input type="button" value="Reload" /></td>
		</tr>
	</table>
</form>
</div>
</body>
</html>
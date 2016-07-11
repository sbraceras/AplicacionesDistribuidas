<%@page import="com.sun.xml.internal.txw2.Document"%>
<%@page import="dtos.JugadorDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "enums.TipoPartido"%>
<%@ page import= "dtos.JugadorDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link type="text/css" rel="stylesheet" href="css/styles.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
	
<link rel="icon" href="dice.png">
	
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Truco Web - Aplicaciones Distribuidas</title>


<%
String apodoJugador = request.getParameter("apodoJugador");
int idJugador = Integer.valueOf(request.getParameter("idJugador")).intValue();

JugadorDTO jugador = new JugadorDTO();

jugador.setApodo(apodoJugador);
jugador.setId(idJugador);

request.setAttribute("jugador", jugador);
%>

<script>

	function buscarPartidoPareja() {
		var apodoJugador1 = document.getElementById("ApodoJugador").value;
		var apodoJugador2 = document.getElementById("ApodoJugador2").value;
		
		if (apodoJugador1 != apodoJugador2){
				location.href='CrearPartidaParejasServlet?idJugador=<%=jugador.getId()%>&apodoJugador=<%=jugador.getApodo()%>&apodoJugador2=' + apodoJugador2;
		}
		
		return false;
	}

	function volverAlMenu(pagina) {
		document.agregarMiembro.action = pagina;
		document.agregarMiembro.submit();
	}

</script>

</head>
<body>
<h1>Truco Web - Aplicaciones Distribuidas - 2016</h1>
<div class="pagina-adm-grupo">
  <div class="form">
  
<!--   DECLARO EL FORMULARIO CON SUS COMPONENTES -->
<!-- EL ACTION, MANDA LA INFORMACION DEL FORM A UN ARCHIVO DESEADO -->
<!-- ONSUBMIT, LO QUE HACE ES EJECUTAR UNA FUNCION JAVASCRIPT CUANDO SE HACE SUBMIT -->
  	
  	<form class="agregar miembro" name="agregarMiembro" id="agregarMiembro" method="post">
		<input type="text" value="<%=jugador.getApodo()%>" id="ApodoJugador" name="ApodoJugador" readonly/>
		<input type="text" value="<%=jugador.getId()%>" id="IdJugador" name="IdJugador" readonly/>
		<input type="text" placeholder="Apodo del otro jugador" id="ApodoJugador2" name="ApodoJugador2"/>
		<input type="button" value="Aceptar Pareja" class="botonAceptar" onclick="return buscarPartidoPareja();"/>

		<div id="divErrorLoginIncompleto">
			<br>
			<br>
			<span id="mensajeErrorLoginIncompleto">Ingrese un nombre valido para el otro jugador de la pareja.</span>
		</div>

		<br>
		<br>

		
     </form>
     <button onclick="volverAlMenu('buscarpartido.jsp?idJugador=<%=jugador.getId()%>&apodoJugador=<%=jugador.getApodo()%>')">VOLVER AL MENU</button>
  </div>
  

</div>
</body>
</html>
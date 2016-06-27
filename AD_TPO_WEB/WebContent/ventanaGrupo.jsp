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
	
<link rel="icon" href="dice.png">
	
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Truco Web - Aplicaciones Distribuidas</title>

<h1>Truco Web - Aplicaciones Distribuidas - 2016</h1>

<%

	String apodoJugador = request.getParameter("apodoJugador");
	Integer idJugador = (Integer) request.getAttribute("idJugador");
	
	JugadorDTO jugador = new JugadorDTO();
	
	jugador.setApodo(apodoJugador);
	jugador.setId(idJugador);
	
	request.setAttribute("jugador", jugador);

%>


<script type="text/javascript">

	function inicioValido() {
		var apodo = document.getElementById("campoApodo").value;
		var contrasena = document.getElementById("campoContrasena").value;

		document.getElementById("divErrorLogin").style.display = 'none';
		if (!apodo || !contrasena) {
			document.getElementById("divErrorLoginIncompleto").style.display = '';
			return false;
		}
		return true;
	}
	
	function registroValido(){
		var apodoR = document.getElementById("campoApodoR").value;
		var contrasenaR = document.getElementById("campoContrasenaR").value;
		var emailR = document.getElementById("campoEmailR").value;

		document.getElementById("divErrorRegistro").style.display = 'none';
		if (!apodoR || !contrasenaR || !emailR) {
			document.getElementById("divErrorRegistroIncompleto").style.display = '';
			return false;
		}
		return true;
	}
	
	function volverMenu(){
	    window.location = "index.jsp";
	    
	    //En realidad tiene que volver a main.jsp pero no esta hecho
	}

</script>
</head>
<body>
<div class="pagina-adm-grupo">
  <div class="form">
  
<!--   DECLARO EL FORMULARIO CON SUS COMPONENTES -->
<!-- EL ACTION, MANDA LA INFORMACION DEL FORM A UN ARCHIVO DESEADO -->
<!-- ONSUBMIT, LO QUE HACE ES EJECUTAR UNA FUNCION JAVASCRIPT CUANDO SE HACE SUBMIT -->
  	
  	<form class="agregar miembro" id="agregarMiembro" action="administrarGrupoServlet" method=post>
      <input type="text" placeholder="Apodo del Miembro" id="ApodoMiembro" name="ApodoMiembro"/>
           <input type="submit" value="Agregar Miembro" class="botonAgregarMiembro"/>
      

		<div id="divErrorLoginIncompleto">
		<br><br>
			<span id="mensajeErrorLoginIncompleto">Ingrese un nombre valido para el grupo.</span>
	    </div>
	    <br><br>
    </form>
     	  <button>Volver al Menu</button>
    
   
    <br><br><br><br>
    <form action = "METERALGOACA///////////////////">
  	<select name="jugadoresAgregar">
    <option value="Gmasaro">GMasaro</option>
    <option value="SBraseras">SBraceras</option>
    <option value="AOrion">AOriont</option>
    <option value="LMessi">LMessi</option>
 	 </select>
  	<br><br>
  	<input type="submit" value="Agregar Jugador" class="botonCrearGrupo">
</form>
  </div>
  

</div>

</body>
</html>
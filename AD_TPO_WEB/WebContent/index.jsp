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

<script type="text/javascript">

	window.onload = mostrarInicioSesion;

	function mostrarInicioSesion(){
		document.getElementById("formRegistro").style.display = 'none';
		document.getElementById("formInicio").style.display = '';
	    document.getElementById("divErrorLoginIncompleto").style.display = 'none';
	}
	
	function mostrarRegistro(){
		document.getElementById("formRegistro").style.display = '';
		document.getElementById("formInicio").style.display = 'none';	
		document.getElementById("divErrorRegistroIncompleto").style.display = 'none';
	}

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

</script>
</head>
<body>

<h1>Truco Web - Aplicaciones Distribuidas - 2016</h1>

<div class="pagina-login">
  <div class="form">
  
<!--   DECLARO EL FORMULARIO CON SUS COMPONENTES -->
<!-- EL ACTION, MANDA LA INFORMACION DEL FORM A UN ARCHIVO DESEADO -->
<!-- ONSUBMIT, LO QUE HACE ES EJECUTAR UNA FUNCION JAVASCRIPT CUANDO SE HACE SUBMIT -->
  	
  	<form class="inicio de sesion" id="formInicio" action="LoginServlet" method=post onsubmit="return inicioValido()">
      <input type="text" placeholder="Apodo" id="campoApodo" name="apodo"/>
      <input type="password" placeholder="Contraseña" id="campoContrasena" name="contrasena"/>
      <input type="submit" value="Iniciar Sesion" class="botonAceptar"/>
      
      <%
      if (session != null && session.getAttribute("resultadoLogin") != null
				&& !((Boolean) session.getAttribute("resultadoLogin"))) {
			session.removeAttribute("resultadoLogin");
	    	%>
	    	<div id="divErrorLogin">
				<span id="mensajeErrorLogin">La informacion ingresada no es correcta, intente nuevamente.</span>
			</div>
	    <%
	    }
	    
	    %>
		<div id="divErrorLoginIncompleto">
			<span id="mensajeErrorLoginIncompleto">Son requeridos un apodo y una contraseña para iniciar sesion.</span>
	    </div>
     	<p class="message" onclick="mostrarRegistro()">¿No estás registrado? <a href="#">Registrarse</a></p>
    </form>
    
   
    <form class="registracion" id="formRegistro" action="RegistroServlet"  method=post onsubmit="return registroValido()">
      <input type="text" placeholder="Apodo" id="campoApodoR" name="apodoR"/>
      <input type="password" placeholder="Contraseña" id="campoContrasenaR" name="contrasenaR"/>
      <input type="text" placeholder="Email@mail.com" id="campoEmailR" name="mailR"/>
      <input type="submit" value="Registrarse" class="botonAceptar"/>
	<%
      if (session != null && session.getAttribute("resultadoRegistro") != null
				&& !((Boolean) session.getAttribute("resultadoRegistro"))) {
			session.removeAttribute("resultadoRegistro");
	    	%>
	    	<div id="divErrorRegistro">
				<span id="mensajeErrorRegistro">No se ha podido registrar al nuevo usuario, intente nuevamente.</span>
			</div>
	    <%
	    }
	    
	    %>

		<div id="divErrorRegistroIncompleto">
				<span id="mensajeErrorRegistroIncompleto">Son requeridos un apodo, una contraseña y un email para registrarse en el sistema.</span>
		</div>
      <p class="message" onclick="mostrarInicioSesion()">¿Ya estás registrado? <a href="#">Iniciar Sesión</a></p>
    </form>
  </div>
</div>

</body>
</html>
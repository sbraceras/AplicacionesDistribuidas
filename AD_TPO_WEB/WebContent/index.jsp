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
<script type="text/javascript">

	window.onload = mostrarInicioSesion;

	function mostrarInicioSesion(){
		document.getElementById("formRegistro").style.display = 'none';
		document.getElementById("formInicio").style.display = '';
	}
	
	function mostrarRegistro(){
		document.getElementById("formRegistro").style.display = '';
		document.getElementById("formInicio").style.display = 'none';	
	}

	function iniciarSesion() {
		var messageRow = document.getElementById("messageRow");
		var apodo = document.getElementById("campoApodo").value;
		var contrasena = document.getElementById("campoContrasena").value;

		if (!apodo || !contrasena) {
			alert("Son requeridos un apodo y una contraseña para iniciar sesion.");
			return false;
		}
		return true;
	}
	
	function registrarse(){
		var messageRow = document.getElementById("messageRow");
		var apodoR = document.getElementById("campoApodoR").value;
		var contrasenaR = document.getElementById("campoContrasenaR").value;
		var emailR = document.getElementById("campoEmailR").value;

		if (!apodoR || !contrasenaR || !emailR) {
			alert("Son requeridos un apodo, una contraseña y un email para registrarse en el sistema.");
			return false;
		}
		return true;
	}

</script>
</head>
<body>
<div class="pagina-login">
  <div class="form">
  	<form class="inicio de sesion" id="formInicio">
      <input type="text" placeholder="Apodo" id="campoApodo"/>
      <input type="password" placeholder="Contraseña" id="campoContrasena"/>
      <button onclick="iniciarSesion()">Aceptar</button>
      <p class="message" onclick="mostrarRegistro()">¿No estás registrado? <a href="#">Registrarse</a></p>
    </form>
    <form class="registracion" id="formRegistro">
      <input type="text" placeholder="Apodo" id="campoApodoR"/>
      <input type="password" placeholder="Contraseña" id="campoContrasenaR"/>
      <input type="text" placeholder="Email@mail.com" id="campoEmailR"/>
      <button onclick="registrarse()">Aceptar</button>
      <p class="message" onclick="mostrarInicioSesion()">¿Ya estás registrado? <a href="#">Iniciar Sesión</a></p>
    </form>
  </div>
</div>

</body>
</html>
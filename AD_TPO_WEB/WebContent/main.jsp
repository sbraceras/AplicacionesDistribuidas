<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<h1>Menu principal</h1>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link type="text/css" rel="stylesheet" href="css/styles.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
	
<link rel="icon" href="dice.png">
	
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Truco Web - Aplicaciones Distribuidas</title>

<script type="text/javascript">

</script>
</head>
<body>
<div class="superior">
</div>
<br>
<div class="menu">
<div class="verticalIzquierdo">
<input type="button" class="boton" value="Buscar Partido" onclick="location.href='buscarpartido.jsp'"/>
</div> 
<div class="verticalCentro">
<input type="button" class="boton" value="Crear Grupo" onclick="location.href='creargrupo.jsp'"/>
</div>
<div class="abajoCentro">
<form method="post" action="LogoutServlet">   
<input type="submit" class="boton" value="Cerrar Sesion"/>
</form>
</div>
</div>
    
</body>
</html>
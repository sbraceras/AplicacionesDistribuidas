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

	function volverMenu(){
	    window.location = "index.jsp";
	}

	function mostrarError(error) {
		document.write(error);
	}

</script>
</head>
<body>
<div class="pagina-crear-grupo">
  <div class="form">
  
<!--   DECLARO EL FORMULARIO CON SUS COMPONENTES -->
<!-- EL ACTION, MANDA LA INFORMACION DEL FORM A UN ARCHIVO DESEADO -->
<!-- ONSUBMIT, LO QUE HACE ES EJECUTAR UNA FUNCION JAVASCRIPT CUANDO SE HACE SUBMIT -->
  	
  	<form class="creacion del grupo" id="creacionGrupo" action="crearGrupoServlet" method=post>
      <input type="text" placeholder="Nombre del Grupo" id="campoNombreGrupo" name="nombreGrupo"/>
      <input type="submit" value="Crear Grupo" class="botonCrearGrupo"/>
      
       <%
	   if (session.getAttribute("error") != null) {
		   String error = (String) session.getAttribute("error");
		   out.write(error);
		   session.removeAttribute("error");
		}
		%>
 	   
		<div id="divErrorLoginIncompleto">	
		<br><br>
			<span id="mensajeErrorLoginIncompleto">Ingrese un nombre valido para el Grupo.</span>
	    </div>
	    <br><br>
    </form>
     	  <button onclick="volverMenu()">VOLVER AL MENU</button>     	  
	</div>
</div>

</body>
</html>
<%@page import="javax.xml.bind.ParseConversionEvent"%>
<%@page import="dtos.JugadorDTO"%>
<%@page import="dtos.MiembroGrupoDTO"%>
<%@page import="dtos.GrupoDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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
	
	GrupoDTO grupo = (GrupoDTO) request.getAttribute("grupo");

	//Voy a buscar la lista de grupos que tiene y los filtro por administrados.//
	if (jugador.getGrupos()!=null){
	
	//mensaje de debug//
	System.out.println("Grupos size: " + jugador.getGrupos().size());
	
	}else{
		System.out.println("Grupos size: 0");
	}
%>

<script>

	window.onload = esconderErrores;

	function esconderErrores(){
		document.getElementById("divError").style.display = 'none';
	}
	
	function confirmarSeleccionGrupo() {
		var selector = document.getElementById("grupoSelectField");
		var nombreGrupo = selector.options[selector.selectedIndex].text;
		var idGrupo = selector.options[selector.selectedIndex].value;
		
		if(idGrupo!=0){
			if (nombreGrupo){
				location.href='SeleccionarJugadoresCerrado?idJugador=<%=jugador.getId()%>&apodoJugador=<%=jugador.getApodo()%>&idGrupo=' + idGrupo + '&nombreGrupo=' + nombreGrupo;
				return true;
				}
		}
		
		document.getElementById("divError").style.display = ''
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
			<form class="seleccionarGrupo" id="seleccionarGrupo">
					<div class="col-sm-10">
					<h4>Seleccione Grupo</h4>
						<select class="selector-grupo-jugador" name="idGrupo" id="grupoSelectField">
							<%if (jugador.getGrupos().isEmpty()){ %>
								<option value=0>
								</option>
							<%} %>
							
							<%
								for (GrupoDTO g : jugador.getGrupos()) {
							%>
							
							<option value=<%=g.getId()%>>
								<%=g.getNombre()%>
							</option>
							<% }%>
						</select> 
						
					</div>
										
					<div id="divError">
						<br>
						<span id="mensajeErrorLoginIncompleto">Debe seleccionar un grupo valido.</span>
					</div>
					<br>
				
			</form>
			<input type="button" value="Seleccionar Grupo" class="botonAceptar" onclick="return confirmarSeleccionGrupo();"/>
			<button onclick="location.href='VolverAlMenu?idJugador=<%=jugador.getId()%>&apodoJugador=<%=jugador.getApodo()%>'">VOLVER AL MENU</button>
		</div>
</div>
			    
</body>
</html>
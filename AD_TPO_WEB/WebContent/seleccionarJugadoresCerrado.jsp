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
	
<link rel="icon" href="dice.png">
	
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Truco Web - Aplicaciones Distribuidas</title>


<%
	JugadorDTO jugador = (JugadorDTO) request.getAttribute("jugador");
	
	//Voy a buscar la lista de grupos que tiene y los filtro por administrados.//
	GrupoDTO grupo = (GrupoDTO) request.getAttribute("grupo");
	List<MiembroGrupoDTO> miembros = (List<MiembroGrupoDTO>) request.getAttribute("miembrosGrupo");
	
	System.out.println("Miembros size: " + miembros.size());
%>

<script>

	window.onload = esconderErrores;

	function esconderErrores(){
		document.getElementById("divError").style.display = 'none';
	}
	
	function confirmarSeleccionJugadores() {
		
		/*Traigo todas las selecciones realizadas*/
		var selectorJ1 = document.getElementById("selectorJugador1");
		var selectorJ2 = document.getElementById("selectorJugador2");
		var selectorJ3 = document.getElementById("selectorJugador3");
		var selectorJ4 = document.getElementById("selectorJugador4");
		
		var nombreJ1 = selectorJ1.options[selectorJ1.selectedIndex].text;
		var nombreJ2 = selectorJ2.options[selectorJ2.selectedIndex].text;
		var nombreJ3 = selectorJ3.options[selectorJ3.selectedIndex].text;
		var nombreJ4 = selectorJ4.options[selectorJ4.selectedIndex].text;
		
		var idJ1 = selectorJ1.options[selectorJ1.selectedIndex].value;
		var idJ2 = selectorJ2.options[selectorJ2.selectedIndex].value;
		var idJ3 = selectorJ3.options[selectorJ3.selectedIndex].value;
		var idJ4 = selectorJ4.options[selectorJ4.selectedIndex].value;
			
		/*Me fijo si no se repite ningun nombre. Ver si existe una manera mas limpia*/		
				
		if(nombreJ1 && nombreJ2 && nombreJ3 && nombreJ4){
			if(nombreJ1!=nombreJ2 && nombreJ1!=nombreJ3 && nombreJ1!=nombreJ4){
				if(nombreJ2!=nombreJ3 && nombreJ2!=nombreJ4){
					if(nombreJ3!=nombreJ4){
						location.href='CrearPartidaCerrada?idJugador1=' + idJ1 + '&apodoJugador1=' + nombreJ1 + '&apodoJugador2=' + nombreJ2 + '&idJugador2=' + idJ2 + '&apodoJugador3=' + nombreJ3 + '&idJugador3=' + nombreJ3 + '&apodoJugador4=' + nombreJ4 + '&idJugador4=' + idJ4 + '&nombreGrupo=<%=grupo.getNombre()%>';
					}
				}
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
			<form class="seleccionarJugadoresCerrado" id="seleccionarJugadoresCerrado">
					<h4>Pareja 1</h4>
					<div class="col-sm-10">
						<select class="selector-grupo-jugador" name="idGrupo" id="selectorJugador1">
							
							<%
								for (MiembroGrupoDTO miembro: miembros) {
							%>
							
							
							<option value=<%=miembro.getId()%>>
								<%=miembro.getJugador()%>
							</option>
							<%
								}
							%>
						</select> 
					</div>
						
					<div class="col-sm-10">
						<select class="selector-grupo-jugador" name="idGrupo" id="selectorJugador2">
							
							<%
								for (MiembroGrupoDTO miembro: miembros) {
							%>
							
							
							<option value=<%=miembro.getId()%>>
								<%=miembro.getJugador()%>
							</option>
							<%
								}
							%>
						</select> 
					</div>
					
					<h4>Pareja 2</h4>
					<div class="col-sm-10">					
						<select class="selector-grupo-jugador" name="idGrupo" id="selectorJugador3">
							
							<%
								for (MiembroGrupoDTO miembro: miembros) {
							%>
							
							
							<option value=<%=miembro.getId()%>>
								<%=miembro.getJugador()%>
							</option>
							<%
								}
							%>
						</select> 
					</div>
						
					<div class="col-sm-10">	
						<select class="selector-grupo-jugador" name="idGrupo" id="selectorJugador4">
							
							<%
								for (MiembroGrupoDTO miembro: miembros) {
							%>
							
							
							<option value=<%=miembro.getId()%>>
								<%=miembro.getJugador()%>
							</option>
							<%
								}
							%>
						</select> 
					</div>	
					
										
					<div id="divError">
						<br>
						<span id="mensajeErrorLoginIncompleto">Debe seleccionar todos jugadores distintos.</span>
					</div>
				<br>
			</form>
			<input type="button" value="Aceptar Jugadores" class="botonAceptar" onclick="return confirmarSeleccionJugadores();"/>
		</div>
</div>
	
<div class="abajoCentro">   
		<input class="botonVolver" id="busquedaVolver" type="submit" value="Volver al Menu" onclick="location.href='VolverAlMenu?idJugador=<%=jugador.getId()%>&apodoJugador=<%=jugador.getApodo()%>'"/>
</div>
		    
</body>
</html>
<%@page import="javax.xml.bind.ParseConversionEvent"%>
<%@page import="dtos.JugadorDTO"%>
<%@page import="dtos.MiembroGrupoDTO"%>
<%@page import="dtos.GrupoDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dtos.PartidoDTO"%>
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
	
	GrupoDTO grupo = (GrupoDTO) request.getAttribute("grupo");
	List<PartidoDTO> partidosGrupo = new ArrayList<PartidoDTO>();
	if(request.getAttribute("hayPartidos")=="1"){
		partidosGrupo = (ArrayList<PartidoDTO>) request.getAttribute("partidosGrupo");
		System.out.println("Partidos size: " + partidosGrupo.size());
	}else{
		System.out.println("Partidos size: 0");
	}
	
%>

<script>

	window.onload = esconderErrores;

	function esconderErrores(){
		document.getElementById("divError").style.display = 'none';
	}
	
	function confirmarSeleccionPartido() {
		
		var selector = document.getElementById("selectorPartido");
		
		var idPartido = selector.options[selector.selectedIndex].value;
		
		if (idPartido==0){
			document.getElementById("divError").style.display = ''
			return false;	
		}
		//
		//
		location.href='SeleccionarJugadoresCerrado?idJugador=<%=jugador.getId()%>&apodoJugador=<%=jugador.getApodo()%>&idGrupo=' + idGrupo + '&nombreGrupo=' + nombreGrupo;
		return true;
	}

	function volverAlMenu(pagina) {
		document.agregarMiembro.action = pagina;
		document.agregarMiembro.submit();
	}

</script>
</head>
<body>

<h1>Truco Web - Aplicaciones Distribuidas - 2016</h1>

<div class="pagina-sel-partido">
		<div class="form">
			<form class="seleccionarPartidoCerrado" id="seleccionarPartidoCerrado">
					<div class="col-sm-10">
						<select class="selector-grupo-jugador" name="idPartido" id="selectorPartido">
							<%if (partidosGrupo.isEmpty()){ %>
							<option value=0>
							</option>
							<%} %>
							
							<%
								for (PartidoDTO p : partidosGrupo) {
							%>
							
							<option value=<%=p.getId()%>>
								<%=grupo.getNombre()%> - <%=p.getParejas().get(0).getJugador1()%> - <%=p.getParejas().get(0).getJugador2()%> VS <%=p.getParejas().get(1).getJugador1()%> - <%=p.getParejas().get(1).getJugador2()%> 
							</option>
							<%
								}
							%>
						</select> 
						
					</div>
										
					<div id="divError">
						<br>
						<span id="mensajeErrorLoginIncompleto">Debe seleccionar un partido valido.</span>
					</div>
					<br>
				
			</form>
			<input type="button" value="Seleccionar Grupo" class="botonAceptar" onclick="return confirmarSeleccionPartido();"/>
		</div>
</div>
	
<div class="abajoCentro">   
		<input class="botonVolver" id="busquedaVolver" type="submit" value="Volver al Menu" onclick="location.href='VolverAlMenu?idJugador=<%=jugador.getId()%>&apodoJugador=<%=jugador.getApodo()%>'"/>
</div>
		    
</body>
</html>
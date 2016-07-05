<%@page import="javax.xml.bind.ParseConversionEvent"%>
<%@page import="dtos.JugadorDTO"%>
<%@page import="dtos.MiembroGrupoDTO"%>
<%@page import="dtos.GrupoDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Grupo: <%= ((GrupoDTO) request.getAttribute("grupo")).getNombre() %></title>

<link type="text/css" rel="stylesheet" href="css/styles.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
		
<link rel="icon" href="images/dice.png">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

</script>
</head>
<body>
<%
	JugadorDTO jugador = (JugadorDTO) request.getAttribute("jugador");
	
	List<MiembroGrupoDTO> miembros = (List<MiembroGrupoDTO>)request.getAttribute("miembrosGrupo");
	
	GrupoDTO grupo = (GrupoDTO) request.getAttribute("grupo");
	
	request.setAttribute("jugador", jugador);
	request.setAttribute("grupo", grupo);
%>

<h1>Truco Web - Aplicaciones Distribuidas - 2016</h1>

<h2 class="TituloGrupo">Grupo</h2> 

<h2 class="TituloMiembros">Miembros del Grupo</h2> 

<table class="TablaGrupo" width="200" border="1">
  <tr>
    <th scope="NombreGrupo">Grupo</th>
    <td><p><%=grupo.getNombre()%></p></td>
  </tr>  
  <tr>
    <th scope="Lider">Lider</th>
    <td><p><%=jugador.getApodo()%></p></td>
  </tr>
</table>


<table class="TablaMiembros" width="412" border="1">
  <tr>
    <th width="81" scope="Miembro">Miembro</th>
    <th width="81" scope="Activo">Estado</th>
    <th width="102" scope="Acciones">Acciones</th>
   </tr>
  <% for(MiembroGrupoDTO miembro: miembros){%>
	  
  <tr>
    <td><%=miembro.getJugador()%></td>
    <td><%if(miembro.isActivo())
    	{
    		%>Activo<%}
    	else{%>Inactivo<%} %></td>
    <td><input type="submit" class="boton2" value="Eliminar" onclick="location.href='AdministrarGrupo?apodoMiembro=<%=miembro.getJugador()%>&idAdministrador=<%=jugador.getId()%>&apodoAdministrador=<%=jugador.getApodo()%>&nombreGrupo=<%=grupo.getNombre()%>&action=eliminar'"/></td>
   </tr>
  <%}%>
</table>

<form class="agregarMiembro" id="agregarMiembro" action="AdministrarGrupo?idAdministrador=<%=jugador.getId()%>&apodoAdministrador=<%=jugador.getApodo()%>&nombreGrupo=<%=grupo.getNombre()%>" method=post>
      <input type="text" placeholder="Apodo del Miembro" id="campoApodo" name="apodoMiembro"/>
      <input type="submit" value="Agregar Miembro" class="botonAgregarMiembro"/>
             
 	   
		<div id="divErrorLoginIncompleto">	
		<br><br>
			<span id="mensaje">Ingrese un nombre valido para el Miembro.</span>
	    </div>
	    <br><br>
    </form>


<div></div>
<input class="botonVolver" type="submit" value="Volver al Menu" onclick="location.href='VolverAlMenu?idJugador=<%=jugador.getId()%>&apodoJugador=<%=jugador.getApodo()%>'"/>    
</body>
</html>
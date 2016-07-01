<%@page import="javax.xml.bind.ParseConversionEvent"%>
<%@page import="dtos.JugadorDTO"%>
<%@page import="dtos.PartidoDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style type="text/css">

.EstiloTabla {
	
	position: absolute;
	top: 100px;
	left: 50px;
	font-family: cursive;
	font-size: 12px;
	font-style: normal;
	font-weight: bold;
	text-transform: uppercase;
	text-align: center;
	color: white;
	text-decoration: underline;
}
.TablaPartidos {
	position: absolute;
	top: 100px;
	left: 350px;
	font-family: cursive;
	font-size: 12px;
	font-style: normal;
	font-weight: bold;
	text-transform: uppercase;
	text-align: center;
	color: white;
	width: 472px;
}
.TituloRanking {
	position: absolute;
	top: 65px;
	left: 110px;
	font-family: Tahoma, Geneva, sans-serif;
	font-size: 16px;
	font-style: normal;
	font-weight: bold;
	font-variant: normal;
	text-transform: uppercase;
	color: #369;
	text-decoration: underline;
}
.PartidosFinalizados {
	position: absolute;
	top: 65px;
	left: 500px;
	font-family: Tahoma, Geneva, sans-serif;
	font-size: 16px;
	font-style: normal;
	font-weight: bold;
	font-variant: normal;
	color: #93C;
	text-decoration: underline;
}


.boton1 {
	width: 200px;
	height: 40px;
	position: absolute;
	left: 50px;
	top: 400px;
	text-transform: uppercase;
	font-weight: bold;
	outline: 0;
	background: #ee0b0d;
	border: 0;
	padding: 15px;
	color: #FFFFFF;
	font-size: 14px;
	-webkit-transition: all 0.3 ease;
	transition: all 0.3 ease;
	cursor: pointer;
	text-align: center;
}

.boton2 {
	width: 400x;
	height: 40px;
	text-transform: uppercase;
	outline: 0;
	font-weight: bold;
	background: #06F;
	border: 0;
	padding: 15px;
	color: #FFFFFF;
	font-size: 14px;
	-webkit-transition: all 0.3 ease;
	transition: all 0.3 ease;
	text-align: center;
}


</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link type="text/css" rel="stylesheet" href="css/styles.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
	
<link rel="icon" href="images/dice.png">
	
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Truco Web - Aplicaciones Distribuidas</title>



<script type="text/javascript">

</script>
</head>
<body>
<%

// 	JugadorDTO jugador = (JugadorDTO) session.getAttribute("user");


	JugadorDTO jugador = (JugadorDTO) request.getAttribute("jugador");
	
	List<PartidoDTO> partidosTerminados = (List<PartidoDTO>)request.getAttribute("partidosTerminados");
%>

<h1>Truco Web - Aplicaciones Distribuidas - 2016</h1>

<h2 class="TituloRanking">Ranking</h2> 

<h2 class="PartidosFinalizados">Partidos Finalizados</h2> 

<table class="EstiloTabla" width="200" border="1">
  <tr>
    <th scope="Categoria">Categoria</th>
    <td><p><%=jugador.getCategoria()%></p></td>
  </tr>
  <tr>
    <th scope="Cantidad de Puntos">Cantidad de Puntos</th>
    <td><%=jugador.getRanking().getPuntos()%></td>
  </tr>
  <tr>
    <th scope="Cantidad Ganadas">Cantidad Ganadas</th>
    <td><%=jugador.getRanking().getCantidadGanadas()%></td>
  </tr>
  <tr>
    <th scope="Promedio">Promedio</th>
    <td><%    
    if(jugador.getRanking().getPuntos()==0)
    {
    	%><%=0%><%	
    }
    else{%>
    <%=jugador.getRanking().getPuntos()/jugador.getRanking().getCantidadGanadas()%><%}%></td>
  </tr>
</table>


<table class="TablaPartidos" width="412" border="1">
  <tr>
    <th width="81" scope="IdPartido">ID_Partido</th>
    <th width="118" scope="Fecha_Juego">Fecha de Juego</th>
    <th width="102" scope="Resultado">Resultado</th>
    <th width="143" scope="Reproducir">Reproducir</th>
  </tr>
  <% for(PartidoDTO part: partidosTerminados){%>
	  
  <tr>
    <td><%=part.getId()%></td>
    <td><%=part.getFechaInicio()%></td>
    <td><% if((part.getParejaGanadora().getJugador1().equals(jugador.getApodo()))||(part.getParejaGanadora().getJugador2().equals(jugador.getApodo())))
    		{
			 %>Ganaste<%}
    	   else %>Perdiste</td>
    <td><input type="submit" class="boton2" value="Reproducir" onclick="location.href='ReproducirPartido?idJugador=<%=jugador.getId()%>&apodoJugador=<%=jugador.getApodo()%>&idPartido=<%=part.getId()%>&ultimoMovimiento=<%=0%>'"/></td>
   </tr>
  <%}%>
</table>


<div></div>
<input type="submit" class="boton1" value="Volver al Menu" onclick="location.href='main.jsp?idJugador=<%=jugador.getId()%>&apodoJugador=<%=jugador.getApodo()%>'"/>
    
</body>
</html>
<%@page import="enums.EstadoPartido"%>
<%@page import="java.util.ArrayList"%>
<%@page import="enums.TipoEnvite"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="dtos.CartaJugadorDTO"%>

<!-- IMPORTO LOS DTOS -->

<%@ page import= "dtos.*"%>
<%@ page import= "java.util.List"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" href="images/dice.png"/>
<title>Partido: <%= ((PartidoDTO) request.getAttribute("miPartido")).getId() %></title>
<style type="text/css">
<!--
body {
  background: #40a3e0; 
  background: -webkit-linear-gradient(right, #76b852, #8DC26F);
  background: -moz-linear-gradient(right, #76b852, #8DC26F);
  background: -o-linear-gradient(right, #76b852, #8DC26F);
  background: linear-gradient(to left, #a1c6ca, #101010);
  font-family: "Roboto", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;  
}
.Estilo1 {color: #f2f3f0;}
.Estilo2 {
	color: #f2f3f0;
	;
	font-weight: bold;
	font-size: 14px;
	height: 80px;
}
-->

#contenedorCarta1 {
	width: 69px;
	height: 96px;
	left: 474px;
	top: 360px;
	border: 1px solid #aaaaaa;
	z-index: 1;
	position: absolute;
	visibility: visible;
}

#contenedorCarta2 {
	width: 69px;
	height: 96px;
	left: 504px;
	top: 340px;
	border: 1px solid #aaaaaa;
	z-index: 2;
	position: absolute;
	visibility: hidden;
}

#contenedorCarta3 {
	width: 69px;
	height: 96px;
	left: 534px;
	top: 320px;
	border: 1px solid #aaaaaa;
	z-index: 3;
	position: absolute;
	visibility: hidden;
}

.tableStyle {
	height: 400px;
}
.tablaMovimientos {
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-top-color: #FFC;
	border-right-color: #FFC;
	border-bottom-color: #FFC;
	border-left-color: #FFC;
}
</style>

</head>

<body>

<!-- Declaro las variables de las cartas para usarlas imagenes -->

<%

JugadorDTO yo = (JugadorDTO) request.getAttribute("jugador");
JugadorDTO jugadorActual = (JugadorDTO) request.getAttribute("jugadorActual");
PartidoDTO miPartido = (PartidoDTO) request.getAttribute("miPartido");
EstadoPartido estadoPartido = (EstadoPartido) request.getAttribute("estadoPartido");

if(!estadoPartido.equals(EstadoPartido.Terminado)){
	
	List<ParejaDTO> parejas = (List<ParejaDTO>) request.getAttribute("parejas");
	List<CartaJugadorDTO> misCartas = (List<CartaJugadorDTO>) request.getAttribute("misCartas");
	List<PuntajeParejaDTO> puntajes = (List<PuntajeParejaDTO>) request.getAttribute("puntajes");
	List<JugadorDTO> ganadoresBazas = (List<JugadorDTO>) request.getAttribute("ganadoresBazas");

	List<TipoEnvite> envites = new ArrayList<TipoEnvite>();	
	if(jugadorActual.getId()== yo.getId()) {
		envites = (List<TipoEnvite>) request.getAttribute("envites");
	}
	
	//Cargamos Nuestras Cartas
	
	String j1c1,j1c2,j1c3;
	
	if(misCartas.get(0).isTirada()==true)
		j1c1 = "images/cartas/vacia2.png";
	else
		j1c1 = "" + misCartas.get(0).getCarta().getPalo() + misCartas.get(0).getCarta().getNumero() + ".png";
	
	
	if(misCartas.get(1).isTirada()==true)
		j1c2 = "images/cartas/vacia2.png";
	else
		j1c2 = "" + misCartas.get(1).getCarta().getPalo() + misCartas.get(1).getCarta().getNumero() + ".png";
	
	
	if(misCartas.get(2).isTirada()==true)
		j1c3 = "images/cartas/vacia2.png";
	else
		j1c3 = "" + misCartas.get(2).getCarta().getPalo() + misCartas.get(2).getCarta().getNumero() + ".png";
	
	j1c1 = "images/cartas/" + j1c1.toUpperCase();
	j1c2 = "images/cartas/" + j1c2.toUpperCase();
	j1c3 = "images/cartas/" + j1c3.toUpperCase();
	
	String j2c1 = "images/cartas/" + "dorso" + ".png";
	String j2c2 = "images/cartas/" + "dorso" + ".png";
	String j2c3 = "images/cartas/" + "dorso" + ".png";
	
	String j3c1 = "images/cartas/" + "dorso" + ".png";
	String j3c2 = "images/cartas/" + "dorso" + ".png";
	String j3c3 = "images/cartas/" + "dorso" + ".png";
	
	String j4c1 = "images/cartas/" + "dorso" + ".png";
	String j4c2 = "images/cartas/" + "dorso" + ".png";
	String j4c3 = "images/cartas/" + "dorso" + ".png";
	%>
	
	
	<!-- Declaro las variables Nombre de jugadores -->
	<%
	
	String jugador1 = yo.getApodo();
	String jugador2 = "N/A";
	String jugador3 = "N/A";
	String jugador4 = "N/A";
	
	int puntosNuestros = 99;
	int puntosEllos = 99;
	
	// 'jugador1' es el que esta ubicado en la parte inferior de la ventana
	if (jugador1.equals((parejas.get(0).getJugador1()))) {
		// soy el jugador 1 de la pareja 1
		// ordeno la mesa a como la veria sentado
		// 'jugador2' es mi jugador a la derecha
		jugador2 = parejas.get(1).getJugador1();
		// 'jugador3' es mi compañero, sentado en frente mio
		jugador3 = parejas.get(0).getJugador2();
		// es mi jugador a la izquierda
		jugador4 = parejas.get(1).getJugador2();
		
		// Pertenezco a la Pareja 1
		puntosNuestros = puntajes.get(0).getPuntaje();
		puntosEllos = puntajes.get(1).getPuntaje();
		
	} else if (jugador1.equals((parejas.get(1).getJugador1()))) {
		// soy el jugador 1 de la pareja 2
		// 'jugador2' es mi jugador a la derecha
		jugador2 = parejas.get(0).getJugador2();
		// 'jugador3' es mi compañero, sentado en frente mio
		jugador3 = parejas.get(1).getJugador2();
		// es mi jugador a la izquierda
		jugador4 = parejas.get(0).getJugador1();
		// Pertenezco a la Pareja 2
		puntosNuestros = puntajes.get(1).getPuntaje();
		puntosEllos = puntajes.get(0).getPuntaje();
	} else if (jugador1.equals((parejas.get(0).getJugador2()))) {
		// soy el jugador 2 de la pareja 1
		// 'jugador2' es mi jugador a la derecha
		jugador2 = parejas.get(1).getJugador2();
		// 'jugador3' es mi compañero, sentado en frente mio
		jugador3 = parejas.get(0).getJugador1();
		// es mi jugador a la izquierda
		jugador4 = parejas.get(1).getJugador1();
		// Pertenezco a la Pareja 1
		puntosNuestros = puntajes.get(0).getPuntaje();
		puntosEllos = puntajes.get(1).getPuntaje();
	} else if (jugador1.equals((parejas.get(1).getJugador2()))) {
		// soy el jugador 2 de la pareja 2
		// 'jugador2' es mi jugador a la derecha
		jugador2 = parejas.get(0).getJugador1();
		// 'jugador3' es mi compañero, sentado en frente mio
		jugador3 = parejas.get(1).getJugador1();
		// es mi jugador a la izquierda
		jugador4 = parejas.get(0).getJugador2();
		// Pertenezco a la Pareja 2
		puntosNuestros = puntajes.get(1).getPuntaje();
		puntosEllos = puntajes.get(0).getPuntaje();
	} else {
		// ?????
	}
%>

<script type="text/javascript">

	setInterval(function() { actualizar() }, 30000); // 4000

	function actualizar() {
		window.location.href='RefrescarPartido?idJugador=<%=yo.getId()%>&apodoJugador=<%=yo.getApodo()%>&idPartido=<%=miPartido.getId()%>'
	}
	
	function cantarEnvite(parametros){
		window.location.href=parametros
	}

	function allowDrop(ev) {
		ev.preventDefault();
	}
	
	function drag(ev) {
		ev.dataTransfer.setData("text", ev.target.id);
	}
	
	function drop1(ev) {
		ev.preventDefault();
		var carta = ev.dataTransfer.getData("text");
		ev.target.appendChild(document.getElementById(carta));
		
		document.getElementById(carta).draggable = false;
		document.getElementById("contenedorCarta1").ondrop = '';
		document.getElementById("contenedorCarta2").style.visibility = 'visible';

		window.location.href='gestionarMovimiento?idJugador=<%=yo.getId()%>&apodoJugador=<%=yo.getApodo()%>&movimiento=ct&idPartido=<%=miPartido.getId()%>&idCartaTirada=' + carta;
	} 

	function drop2(ev) {
		ev.preventDefault();
		var carta = ev.dataTransfer.getData("text");
		ev.target.appendChild(document.getElementById(carta));
		
		document.getElementById(carta).draggable = false;
		document.getElementById("contenedorCarta2").ondrop = '';
		document.getElementById("contenedorCarta3").style.visibility = 'visible';
	} 

	function drop3(ev) {
		ev.preventDefault();
		var carta = ev.dataTransfer.getData("text");
		ev.target.appendChild(document.getElementById(carta));
		
		document.getElementById(carta).draggable = false;
		document.getElementById("contenedorCarta3").ondrop = '';
	}
	
	function habilitarCartas() {

	}

</script>

<table class="Datos" width="1091" height="499" border="0">
  <tr>
    <td width="208" height="55"><div align="left" class="Estilo1"><strong>Turno:</strong>: <%=jugadorActual.getApodo()%></div></td>
    <td colspan="2"><div align="center" class="Estilo1"><strong>TRUCO</strong> <span class="Estilo1">DELUXE</span>  </div></td>
    <td width="222" rowspan="2"><div align="left" class="Estilo2">
      <p><em>PUNTAJES</em></p>
      <p>NOS (<%=jugador1%>/<%=jugador3%>) : <%=puntosNuestros%> <br/>
        ELLOS: (<%=jugador2%>/<%=jugador4%>) <%=puntosEllos%></p>
    </div>      
    <div align="left"></div></td>
  </tr>

  <tr>
    <td height="108"><div align="left"></div></td>
        <td colspan="2"><div align="center"><font color="white"><strong>Jugador 3: <%=jugador3%></strong></font><br />
  <%--     <img src=<%=j3c1%> width="69" height="104" alt="j3c1" /><img src=<%=j3c2%> width="69" height="104" alt="j3c2" /><img src=<%=j3c3%> width="69" height="104" alt="j3c3" />  --%>
    </div></td>
  </tr>
  <tr>
    <td><br />
      <font color="white"> <strong>Jugador 4: <%=jugador4%></strong></font>
    <div align="left">
<%--     <img src=<%=j4c1%> width="69" height="104" alt="j4c1" /><img src=<%=j4c2%> width="69" height="104" alt="j4c2" /><img src=<%=j4c3%> width="69" height="104" alt="j4c3" /> --%>
    </div></td>
    <td colspan="2"><div align="center"><img src="images/mesa.png" width="568" height="297" align="absmiddle" /></div></td>
    <td><font color="white"> <strong>Jugador 2: <%=jugador2%></strong></font><br />
    <div align="left">
<%--     <img src=<%=j2c1%> width="69" height="104" alt="j2c1" /><img src=<%=j2c2%> width="69" height="104" alt="j2c2" /><img src=<%=j2c3%> width="69" height="104" alt="j2c3" /> --%>
    </div></td>
  </tr>
  
  
  <tr>
    <td><div align="left"></div></td>
    <td colspan="2"><div align="center" ><font color="white"> <strong>YO: <%=jugador1%></strong></font></div></td>
    <td><div align="left"></div></td>
  </tr>
  <tr>
    <td><div align="left"></div></td>
    <td width="423"><div align="right">
    <img src=<%=j1c1%> alt="j1c1" name="j1c1" width="69" height="96" id=<%=misCartas.get(0).getCarta().getId()%> draggable=<%=(jugadorActual.getId() == yo.getId() ? "true" : "false")%> ondragstart="drag(event)"/>
    <img src=<%=j1c2%> alt="j1c2" name="j1c2" width="69" height="96" id=<%=misCartas.get(1).getCarta().getId()%> draggable=<%=(jugadorActual.getId() == yo.getId() ? "true" : "false")%> ondragstart="drag(event)"/>
    <img src=<%=j1c3%> alt="j1c3" name="j1c3" width="69" height="96" id=<%=misCartas.get(2).getCarta().getId()%> draggable=<%=(jugadorActual.getId() == yo.getId() ? "true" : "false")%> ondragstart="drag(event)"/>
    </div></td>
    <td width="210"><div align="right">
      <form id="form1" name="form1" method="post" action="">
        <label>
        <% // cargo los Envites para cantar
        	for (TipoEnvite envite: envites) { %>
        		<a href="gestionarMovimiento?movimiento=env&nombreEnvite=<%=envite.name()%>&idJugador=<%=yo.getId()%>&apodoJugador=<%=yo.getApodo()%>&idPartido=<%=miPartido.getId()%>" target="_self"><%=envite%></a>
<%--         		<button onclick=cantarEnvite('gestionarMovimiento?movimiento=env&nombreEnvite=<%=envite.name()%>&idJugador=<%=yo.getId()%>&idPartido=<%=miPartido.getId()%>>)<%=envite%></button> --%>
            <br />        	
        	<%}%>
        </label>
      </form>
      </div></td>
    <td><div align="left">
    
    <% 
    	//Marco los Ganadores de las Bazas
    	JugadorDTO ganador;
    
    	for(int i=0; i<ganadoresBazas.size(); i++)
    	{
    		ganador= ganadoresBazas.get(i);
    		%>Ganador Baza <%=i+1%>: <%=ganador.getApodo()%><br />
    	
    	<% }
    	
		if(ganadoresBazas.size()<3){
			//Aun se esta jugando alguna mano
			%>
			Ganador Baza <%=ganadoresBazas.size()+1%>: En Juego
		<% }
    		
    
    %>
    
<!--     Ganador Baza 1: <br /> -->
<!--       Ganador Baza 2: -->
<!--           <br /> -->
<!--     Ganador Baza 3: </div></td> -->

  </tr>
</table>

<div id="contenedorCarta1" ondrop="drop1(event)" ondragover="allowDrop(event)"></div>
<div id="contenedorCarta2" ondrop="drop2(event)" ondragover="allowDrop(event)"></div>
<div id="contenedorCarta3" ondrop="drop3(event)" ondragover="allowDrop(event)"></div>


	<div  align="left" class="tableStyle" height= "200"="Tabla">
	  <table width="496" id="t01" class="tablaMovimientos">
  <tr>
    <th width="153">TipoMovimiento</th>
    <th width="178">Carta / Envite</th>
    <th width="128">Fecha / Hora</th>
  </tr>
  <tr>
    <td>Jill</td>
    <td>Smith</td>
    <td>50</td>
  </tr>
  <tr>
    <td>Eve</td>
    <td>Jackson</td>
    <td>94</td>
  </tr>
  <tr>
    <td>John</td>
    <td>Doe</td>
    <td>80</td>
  </tr>
</table>
	</div>



<% }

	else
// 		ESTA TERMINADO
		{
		ParejaDTO pareja = (ParejaDTO) request.getAttribute("parejaGanadora");
	%> 
	
	

	
    
 <h1 align="center">El PARTIDO ESTA TERMINADO</h1>   
<h2 align="center">GANO LA PAREJA: <%=pareja.getNumeroPareja()%></h2>
	
	
	<div align="center">Falta codear ir al menu
      <button>Volver al Menu</button>
	
<% }%>

    </div>
</body>
</html>


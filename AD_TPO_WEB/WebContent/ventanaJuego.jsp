<%@ page import="enums.EstadoPartido"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="enums.TipoEnvite"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>

<!-- IMPORTO LOS DTOS -->
<%@ page import="dtos.*"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" href="images/dice.png"/>

<title>Partido: <%= ((PartidoDTO) request.getAttribute("miPartido")).getId() %></title>

<style type="text/css">

	body {
	  background: #40a3e0; 
	  background: -webkit-linear-gradient(right, #76b852, #8DC26F);
	  background: -moz-linear-gradient(right, #76b852, #8DC26F);
	  background: -o-linear-gradient(right, #76b852, #8DC26F);
	  background: linear-gradient(to left, #a1c6ca, #101010);
	  font-family: "Roboto", sans-serif;
	  -webkit-font-smoothing: antialiased;
	  -moz-osx-font-smoothing: grayscale;
/* 	  padding-top: 30px; */
	}

	/*-- css pie de pagina --*/
	footer {
	   position: fixed; /* Hacemos que el pie de pagina tenga una posicion fija */
	   left: 0;
	   bottom: 0;
	   height: 30px;
	   width: 100%; /* hacemos que el pie de pagina ocupe el ancho completo de la página */
	   background-color: #101010;
	   color: #cacaca;
	   text-align: center;
	   padding-bottom: 20px;
	}

	.Estilo1 {
		color: #f2f3f0;
	}
	
	.estiloTabla {
	font-family: "Courier New", Courier, monospace;
	font-size: 10px;
	font-weight: bold;
	color: #FFF;
	}
	
	.TablaResultados {
	
	position: absolute;
	top: 150px;
    left: 10px;
	border-top-style: solid;
	border-right-style: solid;
	border-bottom-style: solid;
	border-left-style: solid;
	border-top-color: #FFC;
	border-right-color: #FFC;
	border-bottom-color: #FFC;
	border-left-color: #FFC;
	color: #FFF;
	}
	
	
	.botonVolver{
	width: 400x;
	height: 40px;
	position: absolute;
	top: 350px;
	left: 50px;
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
	
	.Estilo2 {
		color: #f2f3f0;
		;
		font-weight: bold;
		font-size: 14px;
		height: 80px;
	}
	
	#divReceptorCartaTirada {
		width: 200px;
		height: 150px;
		left: 440px;
		top: 320px;
/* 		border: 2px solid #aaaaaa; */
		position: absolute;
		visibility: visible;
	}

/* 	#contenedorCarta2 { */
/* 		width: 69px; */
/* 		height: 96px; */
/* 		left: 504px; */
/* 		top: 340px; */
/* 		border: 1px solid #aaaaaa; */
/* 		z-index: 2; */
/* 		position: absolute; */
/* 		visibility: hidden; */
/* 	} */
		
/* 	#contenedorCarta3 { */
/* 		width: 69px; */
/* 		height: 96px; */
/* 		left: 534px; */
/* 		top: 320px; */
/* 		border: 1px solid #aaaaaa; */
/* 		z-index: 3; */
/* 		position: absolute; */
/* 		visibility: hidden; */
/* 	} */


	#cartaBaza1Jugador1 {
		position: absolute;
		top: 370px;
		left: 480px;
		width: 69px;
		height: 96px;
	}

	#cartaBaza2Jugador1 {
	    position: absolute;
	    top: 350px;
	    left: 500px;
	    width: 69px;
	    height: 96px;
	}

	#cartaBaza3Jugador1 {
	    position: absolute;
	    top: 330px;
	    left: 520px;
	    width: 69px;
	    height: 96px;
	}

	#cartaBaza1Jugador2 {
		position: absolute;
		top: 280px;
		left: 720px;
		width: 69px;
		height: 96px;
/* 		transform: rotate(-90deg); */

		transition: all .6s ease-in-out;
/* 		transition: width 2s, height 2s, transform 2s; */
	}

	#cartaBaza1Jugador2:HOVER {
		transform: rotate(360deg);
/* 		transform : scale(2); */
	}

	#cartaBaza2Jugador2 {
	    position: absolute;
	    top: 300px;
	    left: 700px;
	    width: 69px;
	    height: 96px;
	}

	#cartaBaza3Jugador2 {
	    position: absolute;
	    top: 320px;
	    left: 680px;
	    width: 69px;
	    height: 96px;
	}

	#cartaBaza1Jugador3 {
		position: absolute;
		top: 210px;
		left: 540px;
		width: 69px;
		height: 96px;
	}

	#cartaBaza2Jugador3 {
	    position: absolute;
	    top: 230px;
	    left: 520px;
	    width: 69px;
	    height: 96px;
	}

	#cartaBaza3Jugador3 {
	    position: absolute;
	    top: 250px;
	    left: 500px;
	    width: 69px;
	    height: 96px;		 
	}

	#cartaBaza1Jugador4 {
		position: absolute;
		top: 320px;
		left: 305px;
		width: 69px;
		height: 96px;
	}

	#cartaBaza2Jugador4 {
	    position: absolute;
	    top: 300px;
	    left: 325px;
	    width: 69px;
	    height: 96px;
	}

	#cartaBaza3Jugador4 {
	    position: absolute;
	    top: 280px;
	    left: 345px;
	    width: 69px;
	    height: 96px;
	}


	@keyframes blink {
	  from { opacity: 1; }
	  to   { opacity: 0; }
	}

	@-webkit-keyframes blink {
	  from { opacity: 1; }
	  to   { opacity: 0; }
	}

	#jugadorActual {
		color: #AAFF00;

		animation-name: blink;
		animation-duration: 1s;
		animation-timing-function: ease-in-out;
		animation-iteration-count: infinite;
		-webkit-animation-name: blink;
		-webkit-animation-duration: 1s;
		-webkit-animation-timing-function: ease-in-out;
		-webkit-animation-iteration-count: infinite;
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
		color: #FFF;
	}
	.estiloTabla {
		font-family: "Courier New", Courier, monospace;
		font-size: 10px;
		font-weight: bold;
		color: #FFF;
	}


</style>

<%

JugadorDTO yo = (JugadorDTO) request.getAttribute("jugador");
EstadoPartido estadoPartido = (EstadoPartido) request.getAttribute("estadoPartido");

if (!estadoPartido.equals(EstadoPartido.Terminado)) {
	
	List<BazaDTO> bazas = (List<BazaDTO>) request.getAttribute("bazas");
	JugadorDTO jugadorActual = (JugadorDTO) request.getAttribute("jugadorActual");
	PartidoDTO miPartido = (PartidoDTO) request.getAttribute("miPartido");
	List<ParejaDTO> parejas = (List<ParejaDTO>) request.getAttribute("parejas");
	List<CartaJugadorDTO> misCartas = (List<CartaJugadorDTO>) request.getAttribute("misCartas");
	List<PuntajeParejaDTO> puntajes = (List<PuntajeParejaDTO>) request.getAttribute("puntajes");
	List<JugadorDTO> ganadoresBazas = (List<JugadorDTO>) request.getAttribute("ganadoresBazas");
// 	List<MovimientoDTO> movimientosBazas = (List<MovimientoDTO>) request.getAttribute("movimientos");

	List<TipoEnvite> envites = new ArrayList<TipoEnvite>();	
	if(jugadorActual.getId()== yo.getId()) {
		envites = (List<TipoEnvite>) request.getAttribute("envites");
	}

	String j1c1,j1c2,j1c3;

	// Cargamos nuestras cartas!
	j1c1 = "images/cartas/" + misCartas.get(0).getCarta().getNombreImagen();
	j1c2 = "images/cartas/" + misCartas.get(1).getCarta().getNombreImagen();
	j1c3 = "images/cartas/" + misCartas.get(2).getCarta().getNombreImagen();

	String dorsoCarta = "images/cartas/" + "dorso" + ".PNG";

	// Declaro las variables Nombre de jugadores
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

	setInterval(function() { actualizar() }, 10000);
// 	setInterval(blinker, 1000);

	function actualizar() {
		window.location.href='RefrescarPartido?idJugador=<%=yo.getId()%>&apodoJugador=<%=yo.getApodo()%>&idPartido=<%=miPartido.getId()%>'
	}

//     function blinker() {
//     	$('.blinking').fadeOut(500);
//     	$('.blinking').fadeIn(500);
//     }

	function allowDrop(ev) {
		ev.preventDefault();
	}

	function drag(ev) {
		ev.dataTransfer.setData("text", ev.target.id);
	}

	function drop(ev) {
		ev.preventDefault();
		var carta = ev.dataTransfer.getData("text");
// 		ev.target.appendChild(document.getElementById(carta));

// 		document.getElementById(carta).draggable = false;
// 		document.getElementById("contenedorCarta1").ondrop = null;
// 		document.getElementById("contenedorCarta2").style.visibility = 'visible';

		window.location.href='gestionarMovimiento?idJugador=<%=yo.getId()%>&apodoJugador=<%=yo.getApodo()%>&movimiento=ct&idPartido=<%=miPartido.getId()%>&idCartaTirada=' + carta;
	} 

// 	function drop2(ev) {
// 		ev.preventDefault();
// 		var carta = ev.dataTransfer.getData("text");
// 		ev.target.appendChild(document.getElementById(carta));

// 		document.getElementById(carta).draggable = false;
// 		document.getElementById("contenedorCarta2").ondrop = null;
// 		document.getElementById("contenedorCarta3").style.visibility = 'visible';
// 	} 

// 	function drop3(ev) {
// 		ev.preventDefault();
// 		var carta = ev.dataTransfer.getData("text");
// 		ev.target.appendChild(document.getElementById(carta));

// 		document.getElementById(carta).draggable = false;
// 		document.getElementById("contenedorCarta3").ondrop = null;
// 	}


</script>

</head>

<body>

<%

// recorremos TODOS los movimientos en TODAS las bazas y vamos colocando las cartas que tiraron en la mesa

byte cantidadCartasTiradasJugador2 = 0;
byte cantidadCartasTiradasJugador3 = 0;
byte cantidadCartasTiradasJugador4 = 0;

for (int i=0; i < bazas.size(); i++) {
	for (MovimientoDTO movimiento: bazas.get(i).getTurnosBaza()) {
		if (movimiento instanceof CartaTiradaDTO) {					
			CartaTiradaDTO cartaTirada = (CartaTiradaDTO) movimiento;
			CartaJugadorDTO cartaJugador = cartaTirada.getCartaJugador(); 
			if (cartaJugador.getJugador().getApodo().equals(jugador1)) {
				// encontre una carta que tire yo!
%>
				<img id=<%="cartaBaza" + bazas.get(i).getNumeroBaza() + "Jugador1"%> src=<%="images/cartas/" + cartaJugador.getCarta().getNombreImagen()%> alt=<%=cartaJugador.getCarta().getNombreImagen()%> draggable="false">
<%
			} else if (cartaJugador.getJugador().getApodo().equals(jugador2)) {
%>
				<img id=<%="cartaBaza" + bazas.get(i).getNumeroBaza() + "Jugador2"%> src=<%="images/cartas/" + cartaJugador.getCarta().getNombreImagen()%> alt=<%=cartaJugador.getCarta().getNombreImagen()%> draggable="false">
<%
				cantidadCartasTiradasJugador2++;

			} else if (cartaJugador.getJugador().getApodo().equals(jugador3)) {
%>
				<img id=<%="cartaBaza" + bazas.get(i).getNumeroBaza() + "Jugador3"%> src=<%="images/cartas/" + cartaJugador.getCarta().getNombreImagen()%> alt=<%=cartaJugador.getCarta().getNombreImagen()%> draggable="false">
<%
				cantidadCartasTiradasJugador3++;

			} else if (cartaJugador.getJugador().getApodo().equals(jugador4)) {
%>
				<img id=<%="cartaBaza" + bazas.get(i).getNumeroBaza() + "Jugador4"%> src=<%="images/cartas/" + cartaJugador.getCarta().getNombreImagen()%> alt=<%=cartaJugador.getCarta().getNombreImagen()%> draggable="false">
<%
				cantidadCartasTiradasJugador4++;

			}
		}
	}
}

%>


<table class="Datos" width="1091" height="499" border="0">
  <tr>
    <td width="220" height="55"><div align="left" class="Estilo1"><strong>Turno:</strong> <%=jugadorActual.getApodo()%></div></td>
    <td colspan="2"><div align="center" class="Estilo1"><strong>TRUCO</strong> <span class="Estilo1">DELUXE</span>  </div></td>
    <td width="222" rowspan="2"><div align="left" class="Estilo2">
      <p><em>PUNTAJES</em></p>
      <p>
		NOS (<%=jugador1%>/<%=jugador3%>) : <%=puntosNuestros%>
      <br/>
      	ELLOS (<%=jugador2%>/<%=jugador4%>) : <%=puntosEllos%>
      </p>
    </div>      
    <div align="left"></div></td>
  </tr>

  <tr>
	<td height="108"></td>
	<td colspan="2">
	<div align="center">

	<font color="white" id=<%=jugadorActual.getApodo().equals(jugador3) ? "jugadorActual" : ""%>> <strong>Jugador 3: <%=jugador3%></strong>

	</div>

	<div align="center">

		<%
		for (int i=0; i < (3-cantidadCartasTiradasJugador3); i++) {
		%>
			<img src=<%=dorsoCarta%> width="69" height="96" alt=<%="j3c" + (i+1)%> draggable="false"/>
		<%
		}
		%>

    </div>
    </td>
  </tr>

  <tr>
    <td>
    <br/>

	<font color="white" id=<%=jugadorActual.getApodo().equals(jugador4) ? "jugadorActual" : ""%>> <strong>Jugador 4: <%=jugador4%></strong>

    <div align="left">

		<%
		for (int i=0; i < (3-cantidadCartasTiradasJugador4); i++) {
		%>
			<img src=<%=dorsoCarta%> width="69" height="96" alt=<%="j4c" + (i+1)%> draggable="false"/>
		<%
		}
		%>

    </div></td>
    <td colspan="2">
	    <div align="center">
	    	<img src="images/mesa.png" width="568" height="297" align="absmiddle" />	    	
	    </div>
    </td>

    <td>

    <font color="white" id=<%=jugadorActual.getApodo().equals(jugador2) ? "jugadorActual" : ""%>> <strong>Jugador 2: <%=jugador2%></strong>

<!--     	<marquee width="150">aguarde por favor...</marquee>  -->

    <br/>
    <div align="left">

		<%
		for (int i=0; i < (3-cantidadCartasTiradasJugador2); i++) {
		%>
	    	<img src=<%=dorsoCarta%> width="69" height="96" alt=<%="j2c" + (i+1)%> draggable="false"/>
	    <%
	    }
	    %>

    </div>
    </td>
  </tr>

  <tr>
    <td><div align="left"></div></td>
    <td colspan="2">
	<div align="center" >

	<font color="white" id=<%=jugadorActual.getApodo().equals(jugador1) ? "jugadorActual" : ""%>> <strong>YO: <%=jugador1%></strong>

	</div>
    </td>
    <td><div align="left"></div></td>
  </tr>
  <tr>
    <td><div align="left"></div></td>
    <td width="423">
    	<div align="right">
    
		<%
		if (!misCartas.get(0).isTirada()) {
		%>
	    	<img src=<%=j1c1%> alt="j1c1" name="j1c1" width="69" height="96" id=<%=misCartas.get(0).getCarta().getId()%> draggable=<%=(jugadorActual.getId() == yo.getId() ? "true" : "false")%> ondragstart="drag(event)" />
	    <%
	    }
	    %>

	    <%
	    if (!misCartas.get(1).isTirada()) {
	    %>
	    	<img src=<%=j1c2%> alt="j1c2" name="j1c2" width="69" height="96" id=<%=misCartas.get(1).getCarta().getId()%> draggable=<%=(jugadorActual.getId() == yo.getId() ? "true" : "false")%> ondragstart="drag(event)" />
	    <%
	    }
	    %>

	    <%
	    if (!misCartas.get(2).isTirada()) {
	    %>
	    	<img src=<%=j1c3%> alt="j1c3" name="j1c3" width="69" height="96" id=<%=misCartas.get(2).getCarta().getId()%> draggable=<%=(jugadorActual.getId() == yo.getId() ? "true" : "false")%> ondragstart="drag(event)" />
	    <%
	    }
	    %>

    	</div>
    </td>

    <td width="210"><div align="center">
        <label>
        <% // cargo los Envites para cantar
        for (TipoEnvite envite: envites) {
        %>
			<input type="submit" value=<%=envite%> onclick="location.href='gestionarMovimiento?movimiento=env&nombreEnvite=<%=envite.name()%>&idJugador=<%=yo.getId()%>&apodoJugador=<%=yo.getApodo()%>&idPartido=<%=miPartido.getId()%>'"
				style="width:100px; height:25px;">
			<br/>
        <%
        }
        %>
        </label>

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

<div id="divReceptorCartaTirada" ondrop="drop(event)" ondragover="allowDrop(event)"></div>

<!-- <div id="contenedorCarta2" ondrop="drop2(event)" ondragover="allowDrop(event)"></div> -->
<!-- <div id="contenedorCarta3" ondrop="drop3(event)" ondragover="allowDrop(event)"></div> -->

<br />
<br />

<div align="left" class="tableStyle" height="200" = "Tabla">


<table width="900" id="t01" class="tablaMovimientos">
  <tr>
    <th width="153" align="left">TipoMovimiento</th>
    <th width="178" align="left">Carta / Envite</th>
    <th width="128" align="left">Fecha / Hora</th>
  </tr>
  
  <% 
  for(BazaDTO baza: bazas){
  	for(MovimientoDTO movimiento: baza.getTurnosBaza())
  	{
	  %>
	  <tr>
	  	<%
	  		if(movimiento instanceof EnviteDTO)
	  		{
				// es un Envite
	  			%><td>Envite</td>
	  			  <td>El Jugador <%=((EnviteDTO)movimiento).getJugador().getApodo()%> canto <%=((EnviteDTO)movimiento).getTipoEnvite()%></td>
	  			  
	  		<% } else {
	  			// es una carta Tirada
	  		%>
	  			 <td>CartaTirada</td>
		  		 <td>El Jugador <%=((CartaTiradaDTO) movimiento).getCartaJugador().getJugador().getApodo()%> tiro el 
		  		 <%=((CartaTiradaDTO) movimiento).getCartaJugador().getCarta().toString()%></td> 
		  	<%}%>

		  	<td><%=movimiento.getFechaHora()%></td>
		  	</tr>  
	<%}
  }%>
	  	
  

</table>
</div>

<% }

else
{
	List<ChicoDTO> puntajes = (List<ChicoDTO>)request.getAttribute("puntajes");
	ParejaDTO ganadora = (ParejaDTO) request.getAttribute("parejaGanadora");
	List<ParejaDTO> parejas = (List<ParejaDTO>) request.getAttribute("parejas");
	
	
	%>
	
	<h1 class="Despedida">El Partido ha finalizado</h1>
	<h2 class="Resultado">La Pareja Ganadora es la de <%=ganadora.getJugador1() +" y " + ganadora.getJugador2()%></h2>
	
	<h3 class="TituloTabla">Los Resultados Fueron: </h3>

		<table class="TablaResultados" width="200" border="1">
		<tr>
	    <th scope="col">N° Chico</th>
	    <th scope="col"><%=parejas.get(0).getJugador1()+"/"+ parejas.get(0).getJugador2()%></th>
	    <th scope="col"><%=parejas.get(1).getJugador1()+"/"+ parejas.get(1).getJugador2()%></th>
	    <th scope="col">Ganador</th>
	</tr>
		
		<% 
			
		for(ChicoDTO chico: puntajes){
			%>
			<tr>
			<th scope="col"><%=chico.getNumeroChico()%></th>
			<th scope="col"><%=chico.getPuntajes().get(0).getPuntaje()%></th>
			<th scope="col"><%=chico.getPuntajes().get(1).getPuntaje()%></th>
			<th scope="col"><%
			
			if(chico.getPuntajes().get(0).getPuntaje()>chico.getPuntajes().get(1).getPuntaje())
			{
				%><%=parejas.get(0).getJugador1()+"/"+ parejas.get(0).getJugador2()%><%
			}
			else
			{
				%><%=parejas.get(1).getJugador1()+"/"+ parejas.get(1).getJugador2()%><%
			}%></th> </tr><%
		}

	%>	
	<input class="botonVolver" type="submit" value="Volver al Menu" onclick="location.href='VolverAlMenu?idJugador=<%=yo.getId()%>&apodoJugador=<%=yo.getApodo()%>'"/>  
		
<%}%>
	

</div>


<footer>
	<p>© 2016 by <b>B</b>raceras, <b>M</b>asaro, <b>M</b>iani, <b>T</b>rejo, <b>Z</b>amudio. Todos los derechos reservados.</p>
</footer>

</body>

</html>

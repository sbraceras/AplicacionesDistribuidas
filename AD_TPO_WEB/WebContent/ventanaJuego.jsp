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
	background-color: #009900;
}
.Estilo1 {color: #FFFFFF}
.Estilo2 {color: #FFFFFF; font-weight: bold; }
-->
</style></head>

<body>

<!-- Declaro las variables de las cartas para usarlas imagenes -->

<%

JugadorDTO yo = (JugadorDTO) request.getAttribute("jugador");
JugadorDTO jugadorActual = (JugadorDTO) request.getAttribute("jugadorActual");
PartidoDTO miPartido = (PartidoDTO) request.getAttribute("miPartido");

List<ParejaDTO> parejas = (List<ParejaDTO>) request.getAttribute("parejas");

List<CartaJugadorDTO> misCartas = (List<CartaJugadorDTO>) request.getAttribute("misCartas");

List<PuntajeParejaDTO> punjates = (List<PuntajeParejaDTO>) request.getAttribute("puntajes");


//Cargamos Nuestras Cartas

String j1c1 = "" + misCartas.get(0).getCarta().getPalo() + misCartas.get(0).getCarta().getNumero() + ".PNG";
String j1c2 = "" + misCartas.get(1).getCarta().getPalo() + misCartas.get(1).getCarta().getNumero() + ".PNG";
String j1c3 = "" + misCartas.get(2).getCarta().getPalo() + misCartas.get(2).getCarta().getNumero() + ".PNG";

j1c1 = "images/cartas/" + j1c1.toUpperCase();
j1c2 = "images/cartas/" + j1c2.toUpperCase();
j1c3 = "images/cartas/" + j1c3.toUpperCase();

String j2c1 = "images/cartas/" + "dorso" + ".PNG";
String j2c2 = "images/cartas/" + "dorso" + ".PNG";
String j2c3 = "images/cartas/" + "dorso" + ".PNG";

String j3c1 = "images/cartas/" + "dorso" + ".PNG";
String j3c2 = "images/cartas/" + "dorso" + ".PNG";
String j3c3 = "images/cartas/" + "dorso" + ".PNG";

String j4c1 = "images/cartas/" + "dorso" + ".PNG";
String j4c2 = "images/cartas/" + "dorso" + ".PNG";
String j4c3 = "images/cartas/" + "dorso" + ".PNG";
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
	jugador2= parejas.get(1).getJugador1();
	// 'jugador3' es mi compañero, sentado en frente mio
	jugador3= parejas.get(0).getJugador2();
	// es mi jugador a la izquierda
	jugador4= parejas.get(1).getJugador2();
	
	//Pertenezco a la Pareja 1
	puntosNuestros = punjates.get(0).getPuntaje();
	puntosEllos = punjates.get(1).getPuntaje();
	
} else if (jugador1.equals((parejas.get(1).getJugador1()))) {
	// soy el jugador 1 de la pareja 2
	// 'jugador2' es mi jugador a la derecha
	jugador2= parejas.get(0).getJugador2();
	// 'jugador3' es mi compañero, sentado en frente mio
	jugador3= parejas.get(1).getJugador2();
	// es mi jugador a la izquierda
	jugador4= parejas.get(0).getJugador1();
	//Pertenezco a la Pareja 2
	puntosNuestros = punjates.get(1).getPuntaje();
	puntosEllos = punjates.get(0).getPuntaje();
} else if (jugador1.equals((parejas.get(0).getJugador2()))) {
	// soy el jugador 2 de la pareja 1
	// 'jugador2' es mi jugador a la derecha
	jugador2= parejas.get(1).getJugador2();
	// 'jugador3' es mi compañero, sentado en frente mio
	jugador3= parejas.get(0).getJugador1();
	// es mi jugador a la izquierda
	jugador4= parejas.get(1).getJugador1();
	//Pertenezco a la Pareja 1
	puntosNuestros = punjates.get(0).getPuntaje();
	puntosEllos = punjates.get(1).getPuntaje();
} else if (jugador1.equals((parejas.get(1).getJugador2()))) {
	// soy el jugador 2 de la pareja 2
	// 'jugador2' es mi jugador a la derecha
	jugador2= parejas.get(0).getJugador1();
	// 'jugador3' es mi compañero, sentado en frente mio
	jugador3= parejas.get(1).getJugador1();
	// es mi jugador a la izquierda
	jugador4= parejas.get(0).getJugador2();
	//Pertenezco a la Pareja 2
	puntosNuestros = punjates.get(1).getPuntaje();
	puntosEllos = punjates.get(0).getPuntaje();
} else {
	// ?????
}

%>

<table width="1091" height="499" border="0">
  <tr>
    <td width="208" height="55"><div align="left" class="Estilo1"><strong>Turno:</strong>: <%=jugadorActual.getApodo()%></div></td>
    <td colspan="2"><div align="center" class="Estilo1"><strong>TRUCO</strong> <span class="Estilo2">DELUXE</span>  </div></td>
    <td width="222"><div align="left" class="Estilo2">NOS: <%=puntosNuestros%> <br/>
    ELLOS: <%=puntosNuestros%> </div></td>
  </tr>
  <tr>
    <td height="108"><div align="left"></div></td>
        <td colspan="2"><div align="center"><font color="white"><strong>Jugador 3</strong></font>: <%=jugador3%><br />
<%--     <img src=<%=j3c1%> width="69" height="104" alt="j3c1" /><img src=<%=j3c2%> width="69" height="104" alt="j3c2" /><img src=<%=j3c3%> width="69" height="104" alt="j3c3" />  --%>
	</div></td>
    <td><div align="left"></div></td>
  </tr>
  <tr>
    <td><br />
      <font color="white"> <strong>Jugador 4: <%=jugador4%></strong></font>
    <div align="left">
<%--     <img src=<%=j4c1%> width="69" height="104" alt="j4c1" /><img src=<%=j4c2%> width="69" height="104" alt="j4c2" /><img src=<%=j4c3%> width="69" height="104" alt="j4c3" /> --%>
    </div></td>
    <td colspan="2"><div align="center"><img src="images/mesa.jpg" width="568" height="297" /><img src="/images/cartas/vacia.PNG" alt="ULTIMA_CARTA" width="69" height="104" align="top" /></div></td>
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
    <img src=<%=j1c1%> width="69" height="104" alt="j1c1" /><img src=<%=j1c2%> width="69" height="104" alt="j1c2" /><img src=<%=j1c3%> width="69" height="104" alt="j1c3" />
    </div></td>
    <td width="210"><div align="right">
      <form id="form1" name="form1" method="post" action="">
        <label>
        <input type="submit" name="Submit" value="Envido" />
        <br />
        <input type="submit" name="Submit5" value="Real Envido" />
        <br />
        <input type="submit" name="Submit52" value="Falta Envido" />
        <br />
        <input type="submit" name="Submit2" value="Truco" />
        <br />
        <input type="submit" name="Submit3" value="Quiero" />
        <br />
        <input type="submit" name="Submit4" value="No Quiero" />
        <br />
        <input type="submit" name="Submit6" value="Re Truco" />
        <br />
        <input type="submit" name="Submit62" value="Mazo" />
        <br />
        <input type="submit" name="Submit622" value="REPARTIR" />
        <br />
          </label>
      </form>
      </div></td>
    <td><div align="left" class="Estilo2">Mano 1: <br />
      Mano 2:
          <br />
    Mano 3: </div></td>
  </tr>
</table>
</body>
</html>
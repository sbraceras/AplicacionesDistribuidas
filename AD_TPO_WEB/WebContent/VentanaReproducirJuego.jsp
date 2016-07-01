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
<title>Partido: <%= ((PartidoDTO) request.getAttribute("partido")).getId() %></title>
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


.estiloTabla {
	font-family: "Courier New", Courier, monospace;
	font-size: 10px;
	font-weight: bold;
	color: #FFF;
}


.botonSiguiente{
	width: 400x;
	height: 40px;
	position: absolute;
	top: 500px;
	right: 300px;
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



div.C1J1{
	position: absolute;
	top: 320px;
	right: 780px;
	width: 69px;
	height: 100px;
		
	 
}

div.C2J1{
	
    position: absolute;
    top: 300px;
    right: 750px;
    width: 69px;
    height: 100px;
    
	
	 
}

div.C3J1{
	
    position: absolute;
    top: 280px;
    right: 720px;
    width: 69px;
    height: 100px;
    
	
	 
}


div.C1J2{
	position: absolute;
	top: 280px;
	right: 600px;
	width: 69px;
	height: 100px;
	
}

div.C2J2{
	
    position: absolute;
    top: 260px;
    right: 570px;
    width: 69px;
    height: 100px;
    
	
	 
}

div.C3J2{
	
    position: absolute;
    top: 240px;
    right: 540px;
    width: 69px;
    height: 100px;
    
	
	 
}

div.C1J3{
	position: absolute;
	top: 200px;
	right: 780px;
	width: 69px;
	height: 100px;
	
}

div.C2J3{
	
    position: absolute;
    top: 180px;
    right: 750px;
    width: 69px;
    height: 100px;
    
	
	 
}

div.C3J3{
	
    position: absolute;
    top: 160px;
    right: 720px;
    width: 69px;
    height: 100px;
    
	
	 
}

div.C1J4{
	position: absolute;
	top: 280px;
	left: 320px;
	width: 69px;
	height: 100px;
	
}

div.C2J4{
	
    position: absolute;
    top: 260px;
    left: 350px;
    width: 69px;
    height: 100px;
    
	
	 
}

div.C3J4{
	
    position: absolute;
    top: 240px;
    left: 380px;
    width: 69px;
    height: 100px;
    
	
	 
}



div.CartasJugador4{
	position: absolute;
	top: 330px;
}

div.CartasJugador3{
	position: absolute;
	top: 40px;
	right: 675px;
	
}

div.CartasJugador2{
	position: absolute;
	top: 330px;
	right: 250px;
}

h1.Despedida {

	position: absolute;
    top: 10px;
    left: 10px;
    
    height: 100px;
    color: white;

}

h2.Resultado {

	position: absolute;
    top: 65px;
    left: 10px;
    
    height: 100px;
    color: white;

}

h3.TituloTabla{

	position: absolute;
    top: 100px;
    left: 10px;
    
    height: 100px;
    color: red;
}


</style>



</head>



<%

JugadorDTO yo = (JugadorDTO) request.getAttribute("jugador");
PartidoDTO partido = (PartidoDTO) request.getAttribute("partido");
String terminado = (String) request.getAttribute("terminado");
List<ParejaDTO> parejas = (List<ParejaDTO>) request.getAttribute("parejas");




%>

<% if(terminado.equals("SI"))
	{
		List<ChicoDTO> puntajes = (List<ChicoDTO>)request.getAttribute("puntajes");
		ParejaDTO ganadora = (ParejaDTO) request.getAttribute("parejaGanadora");
		
		
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
 	<input type="submit" class="botonVolver" value="Volver al Menu" onclick="location.href='main.jsp'"/>
 		
 <%}%>
 
	
<% if(terminado.equals("NO"))
	{
	//el partido no termino
	
		List<MovimientoDTO> movimientos = (List<MovimientoDTO>) request.getAttribute("movimientos");
		List<CartaJugadorDTO> cartas = (List<CartaJugadorDTO>) request.getAttribute("cartasJugador");
		
		
		int ultimoMovimiento = movimientos.get(movimientos.size()-1).getId();
		
// 		Declaro las variables Nombre de jugadores y obtengo a cada uno
				
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
			
			
		} else if (jugador1.equals((parejas.get(1).getJugador1()))) {
			// soy el jugador 1 de la pareja 2
			// 'jugador2' es mi jugador a la derecha
			jugador2 = parejas.get(0).getJugador2();
			// 'jugador3' es mi compañero, sentado en frente mio
			jugador3 = parejas.get(1).getJugador2();
			// es mi jugador a la izquierda
			jugador4 = parejas.get(0).getJugador1();
			// Pertenezco a la Pareja 2

		} else if (jugador1.equals((parejas.get(0).getJugador2()))) {
			// soy el jugador 2 de la pareja 1
			// 'jugador2' es mi jugador a la derecha
			jugador2 = parejas.get(1).getJugador2();
			// 'jugador3' es mi compañero, sentado en frente mio
			jugador3 = parejas.get(0).getJugador1();
			// es mi jugador a la izquierda
			jugador4 = parejas.get(1).getJugador1();
			// Pertenezco a la Pareja 1

		} else if (jugador1.equals((parejas.get(1).getJugador2()))) {
			// soy el jugador 2 de la pareja 2
			// 'jugador2' es mi jugador a la derecha
			jugador2 = parejas.get(0).getJugador1();
			// 'jugador3' es mi compañero, sentado en frente mio
			jugador3 = parejas.get(1).getJugador1();
			// es mi jugador a la izquierda
			jugador4 = parejas.get(0).getJugador2();
			// Pertenezco a la Pareja 2

		} else {
			// ?????
		}
	
		
	List<String> cartasJ1 = new ArrayList<String>();
	List<String> cartasJ2 = new ArrayList<String>();
	List<String> cartasJ3 = new ArrayList<String>();
	List<String> cartasJ4 = new ArrayList<String>();
	List<String> cartasTiradasJ1 = new ArrayList<String>();
	List<String> cartasTiradasJ2 = new ArrayList<String>();
	List<String> cartasTiradasJ3 = new ArrayList<String>();
	List<String> cartasTiradasJ4 = new ArrayList<String>();
	boolean tirada;
	
	//Obtengo las cartas de Cada Jugador
	
	for(CartaJugadorDTO cartaJugador: cartas){
		
		tirada=false;
		//Me fijo si la carta fue tirada 
		for(MovimientoDTO mov: movimientos)
		{
			if((mov instanceof CartaTiradaDTO) && (((CartaTiradaDTO) mov).getCartaJugador().getCarta().equals(cartaJugador.getCarta())))
			{
				tirada=true;
				//tengo que ver a que jugador Pertenece la carta			
				if(cartaJugador.getJugador().getApodo().equals(jugador1))
				{
					cartasJ1.add("images/cartas/vacia2.png");
				}
				else{
					if(cartaJugador.getJugador().getApodo().equals(jugador2))
					{
					cartasJ2.add("images/cartas/vacia2.png");
					}
					else{
						if(cartaJugador.getJugador().getApodo().equals(jugador3))
						{
						cartasJ3.add("images/cartas/vacia2.png");
						}
						else{
							if(cartaJugador.getJugador().getApodo().equals(jugador4))
							{
							cartasJ4.add("images/cartas/vacia2.png");

							}
						}
					}
				}
			}
		}
			if(tirada==false){
				
			//La carta no fue tirada aun
			
				if(cartaJugador.getJugador().getApodo().equals(jugador1))
				{
					cartasJ1.add("images/cartas/" + cartaJugador.getCarta().getPalo()+ cartaJugador.getCarta().getNumero()+ ".png"); 
				}
				else{
					if(cartaJugador.getJugador().getApodo().equals(jugador2))
					{
						cartasJ2.add("images/cartas/" + cartaJugador.getCarta().getPalo()+ cartaJugador.getCarta().getNumero()+ ".png"); 
					}
					else{
						if(cartaJugador.getJugador().getApodo().equals(jugador3))
						{
							cartasJ3.add("images/cartas/" + cartaJugador.getCarta().getPalo()+ cartaJugador.getCarta().getNumero()+ ".png"); 
						}
						else{
							if(cartaJugador.getJugador().getApodo().equals(jugador4))
							{
								cartasJ4.add("images/cartas/" + cartaJugador.getCarta().getPalo()+ cartaJugador.getCarta().getNumero()+ ".png"); 
							}
						}
					}
				}
			}
	}

	
	for(MovimientoDTO mov: movimientos){
		
		if(mov instanceof CartaTiradaDTO){
			
			if(((CartaTiradaDTO)mov).getCartaJugador().getJugador().getApodo().equals(jugador1))
			{
				cartasTiradasJ1.add("images/cartas/" + ((CartaTiradaDTO)mov).getCartaJugador().getCarta().getPalo()+ ((CartaTiradaDTO)mov).getCartaJugador().getCarta().getNumero()+ ".png");
			}
			else{
				if(((CartaTiradaDTO)mov).getCartaJugador().getJugador().getApodo().equals(jugador2))
				{
					cartasTiradasJ2.add("images/cartas/" + ((CartaTiradaDTO)mov).getCartaJugador().getCarta().getPalo()+ ((CartaTiradaDTO)mov).getCartaJugador().getCarta().getNumero()+ ".png");
				}
				else{
					if(((CartaTiradaDTO)mov).getCartaJugador().getJugador().getApodo().equals(jugador3))
					{
						cartasTiradasJ3.add("images/cartas/" + ((CartaTiradaDTO)mov).getCartaJugador().getCarta().getPalo()+ ((CartaTiradaDTO)mov).getCartaJugador().getCarta().getNumero()+ ".png");
					}
					else{
						if(((CartaTiradaDTO)mov).getCartaJugador().getJugador().getApodo().equals(jugador4))
						{
							cartasTiradasJ4.add("images/cartas/" + ((CartaTiradaDTO)mov).getCartaJugador().getCarta().getPalo()+ ((CartaTiradaDTO)mov).getCartaJugador().getCarta().getNumero()+ ".png");
						}
						
					}
				}
			}
		}
	}

%>
	
	
	


<script type="text/javascript">

setInterval(function() { actualizar() }, 4000); 

function actualizar() {
	window.location.href='ReproducirPartido?idJugador=<%=yo.getId()%>&apodoJugador=<%=yo.getApodo()%>&idPartido=<%=partido.getId()%>&ultimoMovimiento=<%=ultimoMovimiento%>'
}


</script>

<table class="Datos" width="1091" height="499" border="0">
  <tr>
    <td colspan="2"><div align="center" class="Estilo1"><strong>TRUCO</strong> <span class="Estilo1">DELUXE</span>  </div></td>
    <td width="222" rowspan="2">
<!--     <div align="left" class="Estilo2"> -->
<!--       <p><em>PUNTAJES</em></p> -->
<%--       <p>NOS (<%=jugador1%>/<%=jugador3%>) : <%=puntosNuestros%> <br/> --%>
<%--         ELLOS: (<%=jugador2%>/<%=jugador4%>) <%=puntosEllos%></p> --%>
<!--     </div>       -->
    <div align="left"></div></td>
  </tr>

  <tr>
    <td height="108"><div align="left"></div></td>
        <td colspan="2"><div class="nombreJugador3"><font color="white"><strong>Jugador 3: <%=jugador3%></strong></font><br />
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
    <td><div class="CartasJugador1"></div></td>
    <td width="423"><div align="right">
    <% for(String carta: cartasJ1)
       {
       %> <img src=<%=carta%> name= "j1c1" width="69" height="96" />
       
       <%} %>
    </div></td>
    
    
    
    
    
<!--     Tal vez agregamos los ganadores de las bazas -->
    

  </tr>
</table>

<div class="CartasJugador2">
    <% for(String carta: cartasJ2)
       {
       %> <img src=<%=carta%> name= "j1c2" width="69" height="96" />
       
       <%} %>
    </div></td>
    
 <div class="CartasJugador3">
    <% for(String carta: cartasJ3)
       {
       %> <img src=<%=carta%> name= "j1c2" width="69" height="96" />
       
       <%} %>
    </div></td>   
    
    
 <div class="CartasJugador4">
    <% for(String carta: cartasJ4)
       {
       %> <img src=<%=carta%> name= "j1c2" width="69" height="96" />
       
       <%} %>
 </div></td>  


<br />
<br />

	<div  align="left" class="tableStyle" height= "200"="Tabla">
	  <table width="900" id="t01" class="tablaMovimientos">
  <tr>
    <th width="153" align="left">TipoMovimiento</th>
    <th width="178" align="left">Carta / Envite</th>
    <th width="128" align="left">Fecha / Hora</th>
  </tr>
  
  <% 

  	for(MovimientoDTO movimiento: movimientos)
  	{
	  %>
	  <tr>
	  	<%
			//Es un Envite	  	
	  		if(movimiento instanceof EnviteDTO)
	  		{
	  			%><td>Envite</td>
	  			  <td>El Jugador <%=((EnviteDTO)movimiento).getJugador().getApodo()%> canto <%=((EnviteDTO)movimiento).getTipoEnvite()%></td>
	  			  
	  			<%} 
	  		//Es una carta Tirada
	  		else
	  			 {%><td>CartaTirada</td>
		  		 <td>El Jugador <%=((CartaTiradaDTO)movimiento).getCartaJugador().getJugador().getApodo()%> lanzo la carta 
		  		 <%=((CartaTiradaDTO)movimiento).getCartaJugador().getCarta().toString()%></td> 
		  		<%}%>
		  	
		  	<td><%=movimiento.getFechaHora()%></td>
		  	</tr>  
	  	
	<%} %>
  

</table>
</div>


<% 
	
	int cantidadTiradasJ1=1; 
for(String carta: cartasTiradasJ1)
	{
	%>
	<div class="C<%=cantidadTiradasJ1%>J1">
	  <a target="_blank">
	    <img src=<%=carta%> width="69" height="96">
	  </a>

	</div><%
	cantidadTiradasJ1++;
	}


int cantidadTiradasJ2=1; 
for(String carta: cartasTiradasJ2)
	{
	%>
	<div class="C<%=cantidadTiradasJ2%>J2">
	  <a target="_blank">
	    <img src=<%=carta%> width="69" height="96">
	  </a>

	</div><%
	cantidadTiradasJ2++;
	}

int cantidadTiradasJ3=1; 
for(String carta: cartasTiradasJ3)
	{
	%>
	<div class="C<%=cantidadTiradasJ3%>J3">
	  <a target="_blank">
	    <img src=<%=carta%> width="69" height="96">
	  </a>

	</div><%
	cantidadTiradasJ3++;
	}


int cantidadTiradasJ4=1; 
for(String carta: cartasTiradasJ4)
	{
	%>
	<div class="C<%=cantidadTiradasJ4%>J4">
	  <img src=<%=carta%> width="69" height="96">
	  </div><%
	cantidadTiradasJ4++;
	}



%>
<td><input type="submit" class="botonSiguiente" value="Siguiente Movimiento" onclick="location.href='ReproducirPartido?idJugador=<%=yo.getId()%>&apodoJugador=<%=yo.getApodo()%>&idPartido=<%=partido.getId()%>&ultimoMovimiento=<%=ultimoMovimiento%>'"/></td>

<% }%>




</html>


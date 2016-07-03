<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import= "enums.TipoPartido"%>
<%@ page import= "dtos.JugadorDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" href="images/dice.png">
<style type="text/css">
.dedos {
	position: absolute;
	top: 110px;
	left: 368px;
	width: 295px;
	height: 191px;
}
#contenedor {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 20px;
	top: 325px;
	position: absolute;
	width: 734px;
	left: 157px;
}
</style>
<title>Esperando jugadores...</title>
<%
	TipoPartido tipoPartido = (TipoPartido) request.getAttribute("tipoPartido");
	JugadorDTO jugador = (JugadorDTO) request.getAttribute("jugador");
	Integer idUltimoPartido = (Integer) request.getAttribute("idUltimoPartido");
%>

<script type="text/javascript">
	setInterval(function() { actualizar() }, 5000); // 4000

	function actualizar() {
		window.location.href='EsperandoPartido?tipoPartido=<%=tipoPartido.name()%>&idJugador=<%=jugador.getId()%>&apodoJugador=<%= jugador.getApodo()%>&idUltimoPartido=<%=idUltimoPartido.intValue()%>'
	}
</script>

</head>

<body>
<p><img src="images/esperando 2.gif" width="268" height="212" class="dedos" align="absbottom"></p>
<p>&nbsp;</p>
<div id="contenedor">
  <div align="center">
    <p>Por favor espere. Se estan buscando jugadores acordes a tu nivel.</p>
  </div>
</div>
<p>
  <label></label>
</p>
<p>&nbsp;</p>
</body>
</html>
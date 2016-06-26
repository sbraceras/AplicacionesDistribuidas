<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import= "enums.TipoPartido"%>
<%@ page import= "dtos.JugadorDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="icon" href="images/dice.png">
<title>Esperando jugadores...</title>
<%
	TipoPartido tipoPartido = (TipoPartido) request.getAttribute("tipoPartido");
	JugadorDTO jugador = (JugadorDTO) request.getAttribute("jugador");
	Integer idUltimoPartido = (Integer) request.getAttribute("idUltimoPartido");
%>

<script type="text/javascript">
	setInterval(function() { actualizar() }, 30000); // 4000

	function actualizar() {
		window.location.href='EsperandoPartido?tipoPartido=<%=tipoPartido.name()%>&idJugador=<%=jugador.getId()%>&apodoJugador=<%= jugador.getApodo()%>&idUltimoPartido=<%=idUltimoPartido.intValue()%>'
	}
</script>

</head>

<body>
	<img src="images/esperando.gif" width="158" height="125" align="middle">
</body>
</html>
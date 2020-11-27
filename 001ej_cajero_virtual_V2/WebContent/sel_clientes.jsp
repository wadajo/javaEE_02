<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.Cliente"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" lang="es">
<link rel="stylesheet" href="./css/stylesheet.css">
<title>Seleccionar cliente</title>
</head>
<body>
<h1>Seleccione el cliente</h1>
<br>
<%
	List<Cliente> clientes=(List<Cliente>)request.getAttribute("clientes");
%>
<form action="Controller?option=toSelCuenta" method="POST">
	Cliente: <select name="dni">
<%
	for (Cliente unCliente : clientes) {
%>
		<option value="<%=unCliente.getDni()%>"><%=unCliente.getNombre()%></option>
<%
	}
%>
	</select>
	<br><br>
	<button type="submit">Ver cuentas pasibles de asociarle</button>
</form>
</body>
</html>
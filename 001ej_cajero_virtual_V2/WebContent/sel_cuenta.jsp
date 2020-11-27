<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.Cuenta"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" lang="es">
<link rel="stylesheet" href="./css/stylesheet.css">
<title>Seleccionar cuenta</title>
</head>
<body>
<h1>Seleccione la cuenta</h1>
<br>
<%
	List<Cuenta> cuentas=(List<Cuenta>)request.getAttribute("cuentas");
%>
<form action="Controller?option=doAsociar" method="POST">
	Cuenta: <select name="idCuenta">
<%
	for (Cuenta unaCuenta : cuentas) {
%>
		<option value="<%=unaCuenta.getNumeroCuenta()%>"><%=unaCuenta.getNumeroCuenta()%></option>
<%
	}
%>
	</select>
	<br><br>
	<button type="submit">Asociar cuenta</button>
</form>
</body>
</html>
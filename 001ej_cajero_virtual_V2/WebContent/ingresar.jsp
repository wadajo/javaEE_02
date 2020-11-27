<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" lang="es">
<link rel="stylesheet" href="./css/stylesheet.css">
<title>Banca Virtual</title>
</head>
<body>
<h1>Ingresar dinero</h1>
<br>
<br>
<h2>Introduzca la cantidad a ingresar en su cuenta nº ${sessionScope.numeroCuenta}</h2>
	Su saldo actual es de ${requestScope.saldo} euros.
	<form action="Controller?option=doIngresarDinero" method="POST">
		Cantidad: <input type="text" name="cantidad" required /><br> <br>
		<button type="submit">Ingresar</button>
	</form>
</body>
</html>
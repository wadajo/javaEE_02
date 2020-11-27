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
<h1>Transferir</h1>
<br>
<br>
<h2>Introduzca la cantidad a transferir desde su cuenta nº ${sessionScope.numeroCuenta}</h2>
	Su saldo actual es de ${requestScope.saldo} euros.
	<form action="Controller?option=doTransferencia" method="POST">
		Cantidad: <input type="text" name="cantidad" required /><br> <br>
		Cuenta destino: <input type="text" name="numeroCuentaDestino" required /><br> <br>
		<button type="submit">Transferir</button>
	</form>
</body>
</html>
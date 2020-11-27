package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String url="login.html";
		String option=request.getParameter("option");
		switch(option) {
		case "doLogin":
			request.getRequestDispatcher("Login").include(request, response);
			url=(Boolean)request.getAttribute("logeado")==true?"index.html":"error_login.html";
			break;
		case "toIngresarDinero":
			request.getRequestDispatcher("CargarSaldo").include(request, response);
			url="ingresar.jsp";
			break;			
		case "doIngresarDinero":
			request.getRequestDispatcher("IngresarDinero").include(request, response);
			url="index.html";
			break;
		case "toExtraerDinero":
			request.getRequestDispatcher("CargarSaldo").include(request, response);
			url="extraer.jsp";
			break;			
		case "doExtraerDinero":
			request.getRequestDispatcher("ExtraerDinero").include(request, response);
			url=(Boolean)request.getAttribute("resultado")==true?"index.html":"error_saldo.html";
			break;
		case "toSelCliente":
			request.getRequestDispatcher("MostrarClientes").include(request, response);
			url="sel_clientes.jsp";
			break;
		case "toSelCuenta":
			request.getRequestDispatcher("MostrarCuentasNoDeCliente").include(request, response);
			url="sel_cuenta.jsp";
			break;
		case "doAsociar":
			request.getRequestDispatcher("AsociarCuentaCliente").include(request, response);			
			break;
		case "toTransferencia":
			request.getRequestDispatcher("CargarSaldo").include(request, response);
			url="transferencia.jsp";
			break;
		case "doTransferencia":
			request.getRequestDispatcher("HacerTransferencia").include(request, response);
			url="index.html";
			break;
		case "toMenu":
			url="index.html";
			break;
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}

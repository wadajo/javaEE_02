package servlets;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import service.BancaService;

@WebServlet("/HacerTransferencia")
public class HacerTransferenciaAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	BancaService service;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int numeroCuentaOrigen=(Integer)request.getSession().getAttribute("numeroCuenta");
		int numeroCuentaDestino=Integer.parseInt(request.getParameter("numeroCuentaDestino"));
		double aExtraer=Double.parseDouble(request.getParameter("cantidad"));
		if(service.comprobarSaldo(numeroCuentaOrigen, aExtraer)) {			
			service.hacerTransferencia(numeroCuentaOrigen,numeroCuentaDestino,aExtraer);
			request.setAttribute("resultado", true);
		} else
			request.setAttribute("resultado", false);
	}

}

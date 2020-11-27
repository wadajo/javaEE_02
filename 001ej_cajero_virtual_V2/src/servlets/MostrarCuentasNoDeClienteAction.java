package servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import service.BancaService;

/**
 * Servlet implementation class MostrarCuentasDeClienteAction
 */
@WebServlet("/MostrarCuentasNoDeCliente")
public class MostrarCuentasNoDeClienteAction extends HttpServlet {
	@Autowired
	BancaService service;	
	private static final long serialVersionUID = 1L;
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		request.setAttribute("cuentas", service.devolverCuentasNoDeCliente(Integer.parseInt(request.getParameter("dni"))));
		session.setAttribute("idCliente", Integer.parseInt(request.getParameter("dni")));
	}

}
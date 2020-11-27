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

@WebServlet("/Login")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	BancaService service;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int numeroCuenta=Integer.parseInt(request.getParameter("cuenta"));
		if (null!=service.devolverCuenta(numeroCuenta)) {
			request.setAttribute("logeado", true);
			request.getSession().setAttribute("numeroCuenta", numeroCuenta);			
		} else 
			request.setAttribute("logeado", false);
	}

}

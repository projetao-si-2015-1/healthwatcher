package br.cin.ufpe.healthwatcher.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.cin.ufpe.healthwatcher.controller.EmployeeLogin;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		EmployeeLogin employeeLogin = (EmployeeLogin) req.getSession().getAttribute("employeeLogin");
		String url = req.getRequestURI();
		
		//se não tiver logado 
		if(employeeLogin == null || !employeeLogin.isLogged()){
			if(url.indexOf("menuEmployee") >= 0){
				res.sendRedirect(req.getServletContext().getContextPath()+"/login.jsf");
			} else {
				chain.doFilter(request, response);
			}
		} else {
			if(employeeLogin.isLogged() && url.indexOf("login.jsf") >= 0){
				res.sendRedirect(req.getServletContext().getContextPath()+"/employee/menuEmployee.jsf");
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void init(FilterConfig filter) throws ServletException {
		// TODO Auto-generated method stub

	}

}

package br.cin.ufpe.healthwatcher.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.cin.ufpe.healthwatcher.model.Employee;
import br.cin.ufpe.healthwatcher.service.EmployeeService;

@ManagedBean(name="employeeLogin")
@SessionScoped
public class EmployeeLogin implements Serializable {

	private static final long serialVersionUID = -8729930869176381346L;

	private Employee employee;
	private boolean logged = false;
	
	@Inject
	private EmployeeService employeeService;	

	@Inject
	private FacesContext facesContext;	
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean isLogged) {
		this.logged = isLogged;
	}
	
	@PostConstruct
	private void init(){
		this.employee = new Employee();
	}

	public String login(){
		if(employee.getLogin()==null || employee.getLogin().trim().equals("")){
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Informe um usuário válido.", "Login inválido!"));
			return "";
		}
		
		Employee emp = employeeService.find(employee.getLogin());
		if(emp==null){
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Senha ou usuário incorretos.", "Login inválido!"));
			return "";			
		}
		BCryptPasswordEncoder crypto = new BCryptPasswordEncoder();
		this.logged = crypto.matches(employee.getPassword(), emp.getPassword());
		if(logged){
			this.employee = emp;
			return "/employee/menuEmployee?faces-redirect=true";
		} else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Senha ou usuário incorretos.", "Login inválido!"));
			return "";
		}
	}
	
	public String logout() {
		this.logged = false;
		return "/home?faces-redirect=true";
	}

	public String changeLoggedUser() {
		this.logged = false;
		return "/login?faces-redirect=true";
	}	
	
}

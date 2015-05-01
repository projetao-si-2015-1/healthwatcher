package br.cin.ufpe.healthwatcher.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.cin.ufpe.healthwatcher.model.Employee;
import br.cin.ufpe.healthwatcher.service.EmployeeService;

@ManagedBean
@SessionScoped
public class EmployeeLogin implements Serializable {

	private static final long serialVersionUID = -8729930869176381346L;

	private Employee employee;
	private boolean logged = false;
	
	@Inject
	private EmployeeService employeeService;	
	
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

	public String login(){
		Employee emp = employeeService.find(employee.getLogin());
		BCryptPasswordEncoder crypto = new BCryptPasswordEncoder();
		this.logged = crypto.matches(employee.getPassword(), emp.getPassword());
		if(logged){
			return "/employee/menuEmployee.jsf?faces-redirect=true";
		} else {
			return "";
		}
	}
	
	public String logout() {
		this.logged = false;
		return "/home.jsf?faces-redirect=true";
	}
	
	@PostConstruct
	private void init(){
		this.employee = new Employee();
	}
	
}

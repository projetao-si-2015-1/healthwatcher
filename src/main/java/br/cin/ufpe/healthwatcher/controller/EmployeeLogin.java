package br.cin.ufpe.healthwatcher.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import br.cin.ufpe.healthwatcher.model.Employee;
import br.cin.ufpe.healthwatcher.service.EmployeeService;

@ManagedBean
@SessionScoped
public class EmployeeLogin implements Serializable {

	private static final long serialVersionUID = -8729930869176381346L;

	private Employee employee;
	public boolean isLogged = false;
	
	@Inject
	private EmployeeService employeeService;	
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public boolean isLogged() {
		return isLogged;
	}

	public void setLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}

	public String login(){
		Employee emp = employeeService.find(employee.getLogin());
		this.isLogged = emp!=null;
		if(isLogged){
			return "/employee/menuEmployee.jsf?faces-redirect=true";
		} else {
			return "";
		}
	}
	
	public String logout() {
		this.isLogged = false;
		return "/home.jsf?faces-redirect=true";
	}
	
	@PostConstruct
	private void init(){
		this.employee = new Employee();
	}
	
}

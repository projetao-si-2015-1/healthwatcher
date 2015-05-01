package br.cin.ufpe.healthwatcher.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.cin.ufpe.healthwatcher.model.Employee;
import br.cin.ufpe.healthwatcher.service.EmployeeService;

@ManagedBean
@SessionScoped
public class EmployeeController implements Serializable {

	private static final long serialVersionUID = -3894035124921216300L;

	private Employee employee;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	private EmployeeService employeeService;
	
	public EmployeeController(){
		
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	@PostConstruct
	private void init(){
		this.employee = new Employee();
	}
	
	public void salvar(){
		try{
			employeeService.insert(employee);
			facesContext.addMessage(null, 
									new FacesMessage(FacesMessage.SEVERITY_INFO, 
													 "Registrado!", 
													 "Registro bem sucedido."));
			init();
		} catch(Exception e){
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Não foi possível registrar a reclamação!", "Registration mal sucedido"));			
		}
	}

	
	
}

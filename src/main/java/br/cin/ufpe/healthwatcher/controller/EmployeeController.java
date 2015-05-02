package br.cin.ufpe.healthwatcher.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.cin.ufpe.healthwatcher.exception.EmployeeAlreadyExistsException;
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
	
	public String salvar(){
		try{
			BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
			this.employee.setPassword(crypt.encode(this.employee.getPassword()));
			employeeService.insert(employee);
			init();
		} catch (EmployeeAlreadyExistsException eaee){
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					eaee.getMessage(), "Registration mal sucedido"));
            return "";
		} catch(Exception e){
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
            						"Registration mal sucedido", "Não foi possível registrar a reclamação!"));
            return "";
		}
		
		return "menuEmployee.xhtml?faces-redirect=true";
	}
	
}

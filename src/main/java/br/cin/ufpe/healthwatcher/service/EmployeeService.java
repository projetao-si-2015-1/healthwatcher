package br.cin.ufpe.healthwatcher.service;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.cin.ufpe.healthwatcher.model.Employee;

public class EmployeeService {

	@Inject
	private EntityManager em;
	
	private static Logger log = LoggerFactory.getLogger(EmployeeService.class);
	
	@Inject
	private Event<Employee> event;
	
	
	public Employee find(String login) {
		Employee e = em.find(Employee.class, login);
		return e;
	}
	
	public boolean login(Employee e){
		Employee employeeDB = findByLoginAndPass(e.getLogin(), e.getPassword());
		return employeeDB!=null;
	}

	public void insert(Employee employee) {
		log.info("Registrando employee " + employee.getName());
		Session session = (Session) em.getDelegate();
		session.persist(employee);
		event.fire(employee);
		
	}
	
	public Employee findByLoginAndPass(String login, String pass){
		Employee e = (Employee) em.createNamedQuery("employeePorLoginSenha")
								   .setParameter("login", login)
								   .setParameter("password", pass)
								   .getSingleResult();
		return e;
	}

}	

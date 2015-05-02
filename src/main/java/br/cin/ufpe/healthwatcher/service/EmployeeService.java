package br.cin.ufpe.healthwatcher.service;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.cin.ufpe.healthwatcher.exception.EmployeeAlreadyExistsException;
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
	
	public void insert(Employee employee) throws EmployeeAlreadyExistsException {
		Session session = (Session) em.getDelegate();
		try{
			log.info("Registrando employee " + employee.getName());
			employee.setEnable(true);
			session.persist(employee);
			session.flush();
			event.fire(employee);
		} catch (ConstraintViolationException cve) {
			log.error("Erro ao inserir employee " + employee.getLogin());
			throw new EmployeeAlreadyExistsException(employee.getLogin());
		}
	}
	
	public void update(Employee employee) {
		log.info("Atualizando " + employee.getName());
		Session session = (Session) em.getDelegate();
		session.merge(employee);
		session.flush();
	}

}	

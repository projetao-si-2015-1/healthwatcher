package br.cin.ufpe.healthwatcher.service;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.cin.ufpe.healthwatcher.model.Employee;

public class EmployeeService {

	@Inject
	private EntityManager em;
	
	public Employee find(String login) {
		Employee e = em.find(Employee.class, login);
		return e;
	}

}

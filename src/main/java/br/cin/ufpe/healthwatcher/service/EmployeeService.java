package br.cin.ufpe.healthwatcher.service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.xml.registry.infomodel.User;

public class EmployeeService {

	@Inject
	private EntityManager em;
	
	public User find(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}

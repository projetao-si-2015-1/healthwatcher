package br.cin.ufpe.healthwatcher.service;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.cin.ufpe.healthwatcher.controller.EmployeeLogin;
import br.cin.ufpe.healthwatcher.model.AnimalComplaint;

@Stateless
public class AnimalComplaintService {

	@Inject
	private EntityManager em;
	
	private static Logger log = LoggerFactory.getLogger(AnimalComplaintService.class);
	
	@Inject
	private Event<AnimalComplaint> event;
	
	@Inject
	private EmployeeLogin employeeLogin;
	
	public void inserir(AnimalComplaint animalComplaint) {
		if(employeeLogin.isLogged()){
			animalComplaint.setAtendente(employeeLogin.getEmployee());
		}
		log.info("Registrando animalComplaint sobre " + animalComplaint.getDescricao());
		Session session = (Session) em.getDelegate();
		session.persist(animalComplaint);
		event.fire(animalComplaint);		
	}

}

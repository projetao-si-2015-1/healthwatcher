package br.cin.ufpe.healthwatcher.service;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.cin.ufpe.healthwatcher.model.Employee;
import br.cin.ufpe.healthwatcher.model.FoodComplaint;

@Stateless
public class FoodComplaintService {

	@Inject
	private EntityManager em;
	
	private static Logger log = LoggerFactory.getLogger(FoodComplaintService.class);
	
	@Inject
	private Event<FoodComplaint> event;
	
	public void inserir(FoodComplaint foodComplaint) {
		//TODO: pegar o employee da sessão de login do sistema ao inves de criar um novo hardcoded
		Employee defaultEmployee = null;
		try{
			defaultEmployee = (Employee) em.createQuery("SELECT e FROM Employee e WHERE e.login = :login")
								.setParameter("login", "admin")
								.getSingleResult();
		}catch(NoResultException nre) {
			log.error("Nenhum employee cadastrado. Cadastrando um novo hardcoded.");
			defaultEmployee.setLogin("admin");
			defaultEmployee.setName("Administrator");
			defaultEmployee.setPassword("123456");
		}
		
		foodComplaint.setAtendente(defaultEmployee);
		log.info("Registrando foodComplaint sobre " + foodComplaint.getDescricao());
		Session session = (Session) em.getDelegate();
		session.persist(foodComplaint);
		event.fire(foodComplaint);
	}

}

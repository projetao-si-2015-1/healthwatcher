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
import br.cin.ufpe.healthwatcher.model.SpecialComplaint;

@Stateless
public class SpecialComplaintService {
	
	@Inject
	private EntityManager em;
	
	private static Logger log = LoggerFactory.getLogger(SpecialComplaintService.class);
	
	@Inject
	private Event<SpecialComplaint> event;
	
	public void inserir(SpecialComplaint specialComplaint){
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
		
		specialComplaint.setAtendente(defaultEmployee);
		log.info("Registrando animalComplaint sobre " + specialComplaint.getDescricao());
		Session session = (Session) em.getDelegate();
		session.persist(specialComplaint);
		event.fire(specialComplaint);				
	}

}

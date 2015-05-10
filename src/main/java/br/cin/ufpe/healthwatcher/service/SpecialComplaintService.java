package br.cin.ufpe.healthwatcher.service;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.cin.ufpe.healthwatcher.controller.EmployeeLogin;
import br.cin.ufpe.healthwatcher.model.SpecialComplaint;

@Stateless
public class SpecialComplaintService {
	
	@Inject
	private EntityManager em;
	
	private static Logger log = LoggerFactory.getLogger(SpecialComplaintService.class);
	
	@Inject
	private Event<SpecialComplaint> event;
	
	@Inject
	private FacesContext facesContext;
	
	public void inserir(SpecialComplaint specialComplaint){
		HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		EmployeeLogin employeeLogin = (EmployeeLogin) req.getSession().getAttribute("employeeLogin");
		if(employeeLogin.isLogged()){
			specialComplaint.setAtendente(employeeLogin.getEmployee());
		}
		log.info("Registrando animalComplaint sobre " + specialComplaint.getDescricao());
		Session session = (Session) em.getDelegate();
		session.persist(specialComplaint);
		event.fire(specialComplaint);				
	}

}

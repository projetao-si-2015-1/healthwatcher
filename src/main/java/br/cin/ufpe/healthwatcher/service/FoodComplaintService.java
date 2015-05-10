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
import br.cin.ufpe.healthwatcher.model.FoodComplaint;

@Stateless
public class FoodComplaintService {

	@Inject
	private EntityManager em;
	
	private static Logger log = LoggerFactory.getLogger(FoodComplaintService.class);
	
	@Inject
	private Event<FoodComplaint> event;
	
	@Inject
	private FacesContext facesContext;
	
	public void inserir(FoodComplaint foodComplaint){
		HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		EmployeeLogin employeeLogin = (EmployeeLogin) req.getSession().getAttribute("employeeLogin");
		if(employeeLogin!=null && employeeLogin.isLogged()){
			foodComplaint.setAtendente(employeeLogin.getEmployee());
		}
		
		log.info("Registrando foodComplaint sobre " + foodComplaint.getDescricao());
		Session session = (Session) em.getDelegate();
		session.persist(foodComplaint);
		event.fire(foodComplaint);
	}

}

package br.cin.ufpe.healthwatcher.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.cin.ufpe.healthwatcher.controller.EmployeeLogin;
import br.cin.ufpe.healthwatcher.model.FoodComplaint;
import br.cin.ufpe.healthwatcher.model.Situacao;

@Stateless
public class FoodComplaintService {

	@Inject
	private EntityManager em;
	
	private static Logger log = LoggerFactory.getLogger(FoodComplaintService.class);
	
	@Inject
	private Event<FoodComplaint> event;
	
	@Inject
	private FacesContext facesContext;
	
	public void update(FoodComplaint foodComplaint){
		log.info("Atualizando complaint " + foodComplaint.getDescricao());
		foodComplaint.setSituacao(Situacao.CLOSED);
		Session session = (Session) em.getDelegate();
		session.merge(foodComplaint);
	}
	
	public void inserir(FoodComplaint foodComplaint){
		HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		EmployeeLogin employeeLogin = (EmployeeLogin) req.getSession().getAttribute("employeeLogin");
		if(employeeLogin!=null && employeeLogin.isLogged()){
			foodComplaint.setAtendente(employeeLogin.getEmployee());
		} else {
			foodComplaint.setAtendente(null);
		}
		
		log.info("Registrando foodComplaint sobre " + foodComplaint.getDescricao());
		Session session = (Session) em.getDelegate();
		session.persist(foodComplaint);
		event.fire(foodComplaint);
	}

	public FoodComplaint find(Integer complaintCode) {
		FoodComplaint foodComplaint = null;
		try{
			foodComplaint = (FoodComplaint) em.createNamedQuery("foodComplaintByCode").setParameter("code", complaintCode).getSingleResult();
		} catch(NoResultException nre) {
			log.warn("FoodComplaint " + complaintCode + " não existe.");
		}
		return foodComplaint;
	}

	@SuppressWarnings("unchecked")
	public List<FoodComplaint> listFoodComplaints() {
		List<FoodComplaint> lista = em.createNamedQuery("allFoodComplaints").getResultList();
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<FoodComplaint> listFoodComplaintsBySituation(Situacao s) {
		List<FoodComplaint> lista = em.createNamedQuery("allFoodComplaintsBySituation")
									  .setParameter("situacao", s)
									  .getResultList();
		return lista;
	}	
	
}

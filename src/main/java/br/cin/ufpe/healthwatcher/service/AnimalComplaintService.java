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
import br.cin.ufpe.healthwatcher.model.AnimalComplaint;
import br.cin.ufpe.healthwatcher.model.Situacao;

@Stateless
public class AnimalComplaintService {

	@Inject
	private EntityManager em;
	
	private static Logger log = LoggerFactory.getLogger(AnimalComplaintService.class);
	
	@Inject
	private Event<AnimalComplaint> event;
	
	@Inject
	private FacesContext facesContext;
	
	public void inserir(AnimalComplaint animalComplaint) {
		HttpServletRequest req = (HttpServletRequest) facesContext.getExternalContext().getRequest();
		EmployeeLogin employeeLogin = (EmployeeLogin) req.getSession().getAttribute("employeeLogin");
		if(employeeLogin!=null && employeeLogin.isLogged()){
			animalComplaint.setAtendente(employeeLogin.getEmployee());
		} else {
			animalComplaint.setAtendente(null);
		}
		log.info("Registrando animalComplaint sobre " + animalComplaint.getDescricao());
		Session session = (Session) em.getDelegate();
		session.persist(animalComplaint);
		event.fire(animalComplaint);		
	}

	public AnimalComplaint find(Integer complaintCode) {
		AnimalComplaint animalComplaint = null;
		try{
			animalComplaint = (AnimalComplaint) em.createNamedQuery("animalComplaintByCode").setParameter("code", complaintCode).getSingleResult();
		} catch(NoResultException nre) {
			log.warn("AnimalComplaint " + complaintCode + " não existe.");
		}
		return animalComplaint;
	}
	
	@SuppressWarnings("unchecked")
	public List<AnimalComplaint> listAnimalComplaints(){
		List<AnimalComplaint> lista = em.createNamedQuery("allAnimalComplaints").getResultList();
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<AnimalComplaint> listAnimalComplaintsBySituation(Situacao situacao){
		List<AnimalComplaint> lista = em.createNamedQuery("animalComplaintsBySituation")
										.setParameter("situacao", situacao)
										.getResultList();
		return lista;
		
	}

}

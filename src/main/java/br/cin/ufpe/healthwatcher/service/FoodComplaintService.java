package br.cin.ufpe.healthwatcher.service;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Session;

import br.cin.ufpe.healthwatcher.model.FoodComplaint;

public class FoodComplaintService {

	@Inject
	private EntityManager em;
	
	public void inserir(FoodComplaint foodComplaint) {
		Session session = (Session) em.getDelegate();
		session.persist(foodComplaint);
	}

}

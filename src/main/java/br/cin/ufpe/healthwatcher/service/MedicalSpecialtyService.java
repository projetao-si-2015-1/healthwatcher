package br.cin.ufpe.healthwatcher.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.cin.ufpe.healthwatcher.model.MedicalSpecialty;

@Stateless
public class MedicalSpecialtyService {
	
	@Inject
	private EntityManager em;

	public MedicalSpecialty find(Integer code) {
		MedicalSpecialty ms = (MedicalSpecialty) em.createNamedQuery("medicalSpecialtyByCode").setParameter("code", code).getSingleResult();
		return ms;
	}
	
	@SuppressWarnings("unchecked")
	public List<MedicalSpecialty> listAllMedicalSpecialties(){
		return em.createNamedQuery("listAllMedicalSpecialties").getResultList();
	}

}

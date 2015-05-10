package br.cin.ufpe.healthwatcher.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.cin.ufpe.healthwatcher.model.HealthUnit;
import br.cin.ufpe.healthwatcher.model.MedicalSpecialty;

@Stateless
public class HealthUnitService {
	
	@Inject
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<HealthUnit> listAllHealthUnits(){
		List<HealthUnit> lista = em.createNamedQuery("allHealthUnits").getResultList();
		return lista;
	}
	
	public List<MedicalSpecialty> listSpecialties(Integer code){
		try{
			HealthUnit healthUnit = (HealthUnit) em.createNamedQuery("healthUnitByName").setParameter("code", code).getSingleResult();
			return healthUnit.getSpecialities();
		} catch	(NoResultException nre){
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<HealthUnit> healthUnitsBySpecialty(Integer code){
		try{
			List<HealthUnit> lista = em.createNamedQuery("healthUnitsBySpecialty").setParameter("code", code).getResultList();
			return lista;
		} catch	(NoResultException nre){
			return null;
		}
		
	}

	public HealthUnit find(String value) {
		HealthUnit hu = em.find(HealthUnit.class, Integer.parseInt(value));
		return hu;
	}

}

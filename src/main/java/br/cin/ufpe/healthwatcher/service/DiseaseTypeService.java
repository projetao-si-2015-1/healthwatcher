package br.cin.ufpe.healthwatcher.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.cin.ufpe.healthwatcher.model.DiseaseType;

public class DiseaseTypeService {

	@Inject
	private EntityManager em;
	
	public DiseaseType find(String value) {
		DiseaseType dt = em.find(DiseaseType.class, Integer.parseInt(value));
		return dt;
	}

	@SuppressWarnings("unchecked")
	public List<DiseaseType> findAll() {
		List<DiseaseType> lista = null;
		try{
			lista = em.createNamedQuery("allDiseases").getResultList();
		} catch(NoResultException nre) {
			
		}
		return lista;
	}

	
}

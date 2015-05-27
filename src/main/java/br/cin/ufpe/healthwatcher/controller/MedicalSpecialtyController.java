package br.cin.ufpe.healthwatcher.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.cin.ufpe.healthwatcher.model.HealthUnit;
import br.cin.ufpe.healthwatcher.model.MedicalSpecialty;
import br.cin.ufpe.healthwatcher.service.HealthUnitService;
import br.cin.ufpe.healthwatcher.service.MedicalSpecialtyService;

@ManagedBean
@SessionScoped
public class MedicalSpecialtyController implements Serializable {
	
	private static final long serialVersionUID = -4955973920455439632L;

	private MedicalSpecialty selectedMedicalSpecialty;
	private List<MedicalSpecialty> specialtiesList;
	private List<HealthUnit> healthUnits;
	
	@Inject
	private MedicalSpecialtyService medicalSpecialtyService;
	
	@Inject
	private HealthUnitService healthUnitService;

	@Inject
	private FacesContext facesContext;	
	
	public MedicalSpecialty getSelectedMedicalSpecialty() {
		return selectedMedicalSpecialty;
	}

	public void setSelectedMedicalSpecialty(MedicalSpecialty selectedMedicalSpecialty) {
		this.selectedMedicalSpecialty = selectedMedicalSpecialty;
	}

	public List<MedicalSpecialty> getSpecialtiesList() {
		return specialtiesList;
	}

	public void setSpecialtiesList(List<MedicalSpecialty> specialtiesList) {
		this.specialtiesList = specialtiesList;
	}

	public List<HealthUnit> getHealthUnits() {
		if(selectedMedicalSpecialty!=null){
			this.healthUnits = healthUnitService.healthUnitsBySpecialty(selectedMedicalSpecialty.getCode());
		}
		return healthUnits;
	}

	public void setHealthUnits(List<HealthUnit> healthUnits) {
		this.healthUnits = healthUnits;
	}

	@PostConstruct
	private void init(){
		this.specialtiesList = medicalSpecialtyService.listAllMedicalSpecialties();
		this.healthUnits = new ArrayList<HealthUnit>();
	}
	
	public String searchHealthUnits(){
		if(selectedMedicalSpecialty!=null){
			return "listHealthUnitsBySpecialty.jsf?faces-redirect=true";
		} else {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Selecione um health unit.", "Erro ao selecionar."));
            return "";			
		}
	}
	

}

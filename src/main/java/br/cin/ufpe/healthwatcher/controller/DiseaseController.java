package br.cin.ufpe.healthwatcher.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import br.cin.ufpe.healthwatcher.model.DiseaseType;
import br.cin.ufpe.healthwatcher.service.DiseaseTypeService;

@ManagedBean
@SessionScoped
public class DiseaseController implements Serializable {
	
	private static final long serialVersionUID = -4955973920455439632L;

	private DiseaseType selectedDiseaseType;
	private List<DiseaseType> diseaseTypes;
	
	@Inject
	private DiseaseTypeService diseaseTypeService;
	
	@PostConstruct
	private void init(){
	}

	public List<DiseaseType> getDiseaseTypes() {
		if(this.diseaseTypes==null){
			this.diseaseTypes = diseaseTypeService.findAll();
		}
		return diseaseTypes;
	}

	public void setDiseaseTypes(List<DiseaseType> diseaseTypes) {
		this.diseaseTypes = diseaseTypes;
	}

	public DiseaseType getSelectedDiseaseType() {
		return selectedDiseaseType;
	}

	public void setSelectedDiseaseType(DiseaseType selectedDiseaseType) {
		this.selectedDiseaseType = selectedDiseaseType;
	}
	
	public String searchDisease(){
		return "searchDiseaseData?faces-redirect=true";
	}

}

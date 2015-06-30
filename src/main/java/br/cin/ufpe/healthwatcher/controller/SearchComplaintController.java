package br.cin.ufpe.healthwatcher.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.cin.ufpe.healthwatcher.model.Complaint;
import br.cin.ufpe.healthwatcher.model.Situacao;
import br.cin.ufpe.healthwatcher.service.AnimalComplaintService;
import br.cin.ufpe.healthwatcher.service.FoodComplaintService;
import br.cin.ufpe.healthwatcher.service.SpecialComplaintService;

@ManagedBean
@ViewScoped
public class SearchComplaintController implements Serializable {

	private static final long serialVersionUID = -6887424307646650506L;
	
	private Complaint complaint;
	private List<Complaint> complaints;
	private boolean foodComplaintFlag = false;
	private boolean animalComplaintFlag = false;
	private boolean specialComplaintFlag = false;
	private boolean noDataFound;
	
	@Inject
	private FoodComplaintService foodComplaintService;
	
	@Inject
	private AnimalComplaintService animalComplaintService;
	
	@Inject
	private SpecialComplaintService specialComplaintService;
	
	@PostConstruct
	private void init(){
		this.complaints.addAll(animalComplaintService.listAnimalComplaintsBySituation(Situacao.OPEN));
		this.complaints.addAll(foodComplaintService.listFoodComplaintsBySituation(Situacao.OPEN));
		this.complaints.addAll(specialComplaintService.listSpecialComplaintsBySituation(Situacao.OPEN));
	}

	public boolean isFoodComplaintFlag() {
		return foodComplaintFlag;
	}

	public void setFoodComplaintFlag(boolean foodComplaintFlag) {
		this.foodComplaintFlag = foodComplaintFlag;
	}

	public boolean isAnimalComplaintFlag() {
		return animalComplaintFlag;
	}

	public void setAnimalComplaintFlag(boolean animalComplaintFlag) {
		this.animalComplaintFlag = animalComplaintFlag;
	}

	public boolean isSpecialComplaintFlag() {
		return specialComplaintFlag;
	}

	public void setSpecialComplaintFlag(boolean specialComplaintFlag) {
		this.specialComplaintFlag = specialComplaintFlag;
	}

	public boolean isNoDataFound() {
		noDataFound = this.complaints.size() > 0;
		return noDataFound;
	}

	public void setNoDataFound(boolean noDataFound) {
		this.noDataFound = noDataFound;
	}

	public Complaint getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}

	public List<Complaint> getComplaints() {
		return complaints;
	}

	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}

}

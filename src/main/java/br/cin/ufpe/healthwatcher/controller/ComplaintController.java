package br.cin.ufpe.healthwatcher.controller;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.cin.ufpe.healthwatcher.model.AnimalComplaint;
import br.cin.ufpe.healthwatcher.model.FoodComplaint;
import br.cin.ufpe.healthwatcher.model.SpecialComplaint;
import br.cin.ufpe.healthwatcher.service.AnimalComplaintService;
import br.cin.ufpe.healthwatcher.service.FoodComplaintService;
import br.cin.ufpe.healthwatcher.service.SpecialComplaintService;

@ApplicationScoped
public class ComplaintController implements Serializable {

	private static final long serialVersionUID = -6887424307646650506L;
	
	private Integer complaintCode;
	private FoodComplaint foodComplaint;
	private AnimalComplaint animalComplaint;
	private SpecialComplaint specialComplaint;
	private boolean foodComplaintFlag = false;
	private boolean animalComplaintFlag = false;
	private boolean specialComplaintFlag = false;
	private boolean noDataFound = true;
	
	@Inject
	private FoodComplaintService foodComplaintService;
	
	@Inject
	private AnimalComplaintService animalComplaintService;
	
	@Inject
	private SpecialComplaintService specialComplaintService;

	public Integer getComplaintCode() {
		return complaintCode;
	}

	public void setComplaintCode(Integer complaintCode) {
		this.complaintCode = complaintCode;
	}
	
	public FoodComplaint getFoodComplaint() {
		return foodComplaint;
	}

	public void setFoodComplaint(FoodComplaint foodComplaint) {
		this.foodComplaint = foodComplaint;
	}

	public AnimalComplaint getAnimalComplaint() {
		return animalComplaint;
	}

	public void setAnimalComplaint(AnimalComplaint animalComplaint) {
		this.animalComplaint = animalComplaint;
	}

	public SpecialComplaint getSpecialComplaint() {
		return specialComplaint;
	}

	public void setSpecialComplaint(SpecialComplaint specialComplaint) {
		this.specialComplaint = specialComplaint;
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

	public String searchComplaint(){
		this.foodComplaint = foodComplaintService.find(this.complaintCode);
		if(this.foodComplaint==null){
			this.animalComplaint = animalComplaintService.find(this.complaintCode);
			if(this.animalComplaint==null){
				this.specialComplaint = specialComplaintService.find(this.complaintCode);
				if(this.specialComplaint!=null){
					this.specialComplaintFlag = true;
				}
			} else {
				this.animalComplaintFlag = true;
			}
		} else {
			this.foodComplaintFlag = true;
		}
		this.noDataFound = this.animalComplaint==null && this.foodComplaint==null && this.specialComplaint==null;
		return "searchComplaintData.jsf?faces-redirect=true";
	}

	public boolean isNoDataFound() {
		return noDataFound;
	}

	public void setNoDataFound(boolean noDataFound) {
		this.noDataFound = noDataFound;
	}
	

}

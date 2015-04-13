package br.cin.ufpe.healthwatcher.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import br.cin.ufpe.healthwatcher.model.Address;
import br.cin.ufpe.healthwatcher.model.Employee;
import br.cin.ufpe.healthwatcher.model.FoodComplaint;
import br.cin.ufpe.healthwatcher.service.FoodComplaintService;

@ManagedBean
@SessionScoped
public class FoodComplaintController {
	
	@Inject
	private FoodComplaintService foodComplaintService;
	
	private FoodComplaint foodComplaint;
	
	@PostConstruct
	public void init(){
		foodComplaint = new FoodComplaint();
		foodComplaint.setAtendente(new Employee());
		foodComplaint.setEnderecoDoente(new Address());
		foodComplaint.setEnderecoSolicitante(new Address());
	}

	public FoodComplaint getFoodComplaint() {
		return foodComplaint;
	}

	public void setFoodComplaint(FoodComplaint foodComplaint) {
		this.foodComplaint = foodComplaint;
	}
	
	public String salvar(){
		foodComplaintService.inserir(foodComplaint);
		return "specifyComplaint.xhtml?faces-redirect=true";
	}

}

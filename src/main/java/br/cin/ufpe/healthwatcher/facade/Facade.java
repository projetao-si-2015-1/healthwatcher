package br.cin.ufpe.healthwatcher.facade;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.inject.Named;

import br.cin.ufpe.healthwatcher.controller.AnimalComplaintController;
import br.cin.ufpe.healthwatcher.controller.ComplaintController;
import br.cin.ufpe.healthwatcher.controller.DiseaseController;
import br.cin.ufpe.healthwatcher.controller.EmployeeController;
import br.cin.ufpe.healthwatcher.controller.EmployeeLogin;
import br.cin.ufpe.healthwatcher.controller.FoodComplaintController;
import br.cin.ufpe.healthwatcher.controller.HealthUnitController;
import br.cin.ufpe.healthwatcher.controller.MedicalSpecialtyController;
import br.cin.ufpe.healthwatcher.controller.SpecialComplaintController;

@Singleton
@Named
public class Facade {
	
	@Inject
	private AnimalComplaintController animalComplaintController;
	
	@Inject
	private FoodComplaintController foodComplaintController;
	
	@Inject
	private SpecialComplaintController specialComplaintController;
	
	@Inject
	private HealthUnitController healthUnitController;
	
	@Inject
	private MedicalSpecialtyController medicalSpecialtyController;
	
	@Inject
	private ComplaintController complaintController;
	
	@Inject
	private DiseaseController diseaseController;
	
	@Inject
	private EmployeeLogin employeeLogin;
	
	@Inject
	private EmployeeController employeeController;

	public AnimalComplaintController getAnimalComplaintController() {
		if(animalComplaintController == null){
			animalComplaintController = new AnimalComplaintController();
		}
		return animalComplaintController;
	}

	public FoodComplaintController getFoodComplaintController() {
		if(foodComplaintController==null){
			foodComplaintController = new FoodComplaintController();
		}
		return foodComplaintController;
	}

	public SpecialComplaintController getSpecialComplaintController() {
		if(specialComplaintController==null){
			specialComplaintController = new SpecialComplaintController();
		}
		return specialComplaintController;
	}

	public HealthUnitController getHealthUnitController() {
		if(healthUnitController==null){
			healthUnitController = new HealthUnitController();
		}
		return healthUnitController;
	}

	public MedicalSpecialtyController getMedicalSpecialtyController() {
		if(medicalSpecialtyController==null){
			medicalSpecialtyController = new MedicalSpecialtyController();
		}
		return medicalSpecialtyController;
	}

	public ComplaintController getComplaintController() {
		if(complaintController==null){
			complaintController = new ComplaintController();
		}
		return complaintController;
	}

	public DiseaseController getDiseaseController() {
		if(diseaseController==null){
			diseaseController = new DiseaseController();
		}
		return diseaseController;
	}

	public EmployeeLogin getEmployeeLogin() {
		if(employeeLogin==null){
			employeeLogin = new EmployeeLogin();
		}
		return employeeLogin;
	}

	public EmployeeController getEmployeeController() {
		if(employeeController==null){
			employeeController = new EmployeeController();
		}
		return employeeController;
	}

}
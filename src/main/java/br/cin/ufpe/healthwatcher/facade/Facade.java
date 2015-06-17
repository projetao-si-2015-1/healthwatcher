package br.cin.ufpe.healthwatcher.facade;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.inject.Named;

import br.cin.ufpe.healthwatcher.controller.AnimalComplaintController;
import br.cin.ufpe.healthwatcher.controller.FoodComplaintController;
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

}
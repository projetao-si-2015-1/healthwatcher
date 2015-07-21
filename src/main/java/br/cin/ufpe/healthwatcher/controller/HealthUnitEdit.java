package br.cin.ufpe.healthwatcher.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.cin.ufpe.healthwatcher.model.HealthUnit;
import br.cin.ufpe.healthwatcher.service.HealthUnitService;

@ManagedBean
@ViewScoped
public class HealthUnitEdit implements Serializable {

	private static final long serialVersionUID = -4914101471627136696L;
	
	private List<HealthUnit> healthUnits;
	private HealthUnit selectedHealthUnit;
	
	@Inject
	private HealthUnitService healthUnitService;
	
	@PostConstruct
	private void init(){
		Integer code = (Integer) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("healthunit");
		if(code!=null){
			this.selectedHealthUnit = healthUnitService.find(String.valueOf(code));
		}
		this.healthUnits = healthUnitService.listAllHealthUnits();
	}

	public List<HealthUnit> getHealthUnits() {
		return healthUnits;
	}

	public void setHealthUnits(List<HealthUnit> healthUnits) {
		this.healthUnits = healthUnits;
	}

	public HealthUnit getSelectedHealthUnit() {
		return selectedHealthUnit;
	}

	public void setSelectedHealthUnit(HealthUnit selectedHealthUnit) {
		this.selectedHealthUnit = selectedHealthUnit;
	}
	
	public String editHealthUnit(){
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("healthunit", this.selectedHealthUnit.getCode());
		return "updateHealthUnitSearch?faces-redirect=true";
	}
	
	public String atualizar(){
		healthUnitService.update(selectedHealthUnit);
		return "updateHealthUnitData?faces-redirect=true";
	}

}

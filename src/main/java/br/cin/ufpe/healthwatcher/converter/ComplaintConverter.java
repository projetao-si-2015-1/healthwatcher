package br.cin.ufpe.healthwatcher.converter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import br.cin.ufpe.healthwatcher.model.Complaint;
import br.cin.ufpe.healthwatcher.service.AnimalComplaintService;
import br.cin.ufpe.healthwatcher.service.FoodComplaintService;
import br.cin.ufpe.healthwatcher.service.SpecialComplaintService;

@ManagedBean
@RequestScoped
public class ComplaintConverter implements Converter {
	
	@Inject
	private FoodComplaintService foodComplaintService;
	
	@Inject
	private AnimalComplaintService animalComplaintService;
	
	@Inject
	private SpecialComplaintService specialComplaintService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,	String value) {
		if(value!=null){
			Complaint complaint = foodComplaintService.find(Integer.parseInt(value));
			if(complaint==null){
				complaint = animalComplaintService.find(Integer.parseInt(value));
			}
			if(complaint==null){
				complaint = specialComplaintService.find(Integer.parseInt(value));
			}
			return complaint;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,	Object value) {
		String code = String.valueOf(((Complaint) value).getCodigo());
		return code;
	}
}

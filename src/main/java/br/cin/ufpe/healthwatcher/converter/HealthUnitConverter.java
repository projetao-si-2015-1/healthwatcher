package br.cin.ufpe.healthwatcher.converter;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import br.cin.ufpe.healthwatcher.model.HealthUnit;
import br.cin.ufpe.healthwatcher.service.HealthUnitService;

@ManagedBean
@SessionScoped
public class HealthUnitConverter implements Converter, Serializable {

	private static final long serialVersionUID = 391558762793887877L;
	
	@Inject
	private HealthUnitService healthUnitService;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,	String value) {
		if(value!=null){
			HealthUnit hu = healthUnitService.find(value);
			return hu;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,	Object value) {
		String code = String.valueOf(((HealthUnit) value).getCode());
		return code;
	}

}

package br.cin.ufpe.healthwatcher.view;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.cin.ufpe.healthwatcher.model.Address;
import br.cin.ufpe.healthwatcher.model.AnimalComplaint;
import br.cin.ufpe.healthwatcher.model.Employee;
import br.cin.ufpe.healthwatcher.model.Situacao;
import br.cin.ufpe.healthwatcher.service.AnimalComplaintService;

@ManagedBean
@ViewScoped
public class AnimalComplaintView implements Serializable {
	
	private static final long serialVersionUID = 7502327389893929089L;

	@Inject
	private AnimalComplaintService animalComplaintService;
	
	@Inject
	private FacesContext facesContext;
	
	private AnimalComplaint animalComplaint;
	
	@PostConstruct
	public void init(){
		animalComplaint = new AnimalComplaint();
		animalComplaint.setAtendente(new Employee());
		animalComplaint.setOccurenceLocalAddress(new Address());
		animalComplaint.setEnderecoSolicitante(new Address());
	}

	public AnimalComplaint getAnimalComplaint() {
		return animalComplaint;
	}

	public void setAnimalComplaint(AnimalComplaint animalComplaint) {
		this.animalComplaint = animalComplaint;
	}
	
	public String salvar(){
		try{
			this.animalComplaint.setDataParecer(new Date());
			this.animalComplaint.setDataQueixa(new Date());
			this.animalComplaint.setSituacao(Situacao.OPEN);
			animalComplaintService.inserir(animalComplaint);
			facesContext.getExternalContext().getFlash().put("codigo", animalComplaint.getCodigo());
			return "animalComplaintInserted?faces-redirect=true";
		} catch(Exception e){
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Não foi possível registrar a reclamação!", "Registration mal sucedido"));
            return "";
		}
	}

}

package br.cin.ufpe.healthwatcher.controller;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.cin.ufpe.healthwatcher.model.Address;
import br.cin.ufpe.healthwatcher.model.Situacao;
import br.cin.ufpe.healthwatcher.model.SpecialComplaint;
import br.cin.ufpe.healthwatcher.model.Employee;
import br.cin.ufpe.healthwatcher.service.SpecialComplaintService;

@ManagedBean
@SessionScoped
public class SpecialComplaintController implements Serializable {
	
	private static final long serialVersionUID = -5104908221615000012L;

	@Inject
	private SpecialComplaintService specialComplaintService;
	
	@Inject
	private FacesContext facesContext;
	
	private SpecialComplaint specialComplaint;
	
	@PostConstruct
	public void init(){
		specialComplaint = new SpecialComplaint();
		specialComplaint.setAtendente(new Employee());
		specialComplaint.setEnderecoSolicitante(new Address());
		specialComplaint.setEnderecoOcorrencia(new Address());
	}

	public SpecialComplaint getSpecialComplaint() {
		return specialComplaint;
	}

	public void setSpecialComplaint(SpecialComplaint specialComplaint) {
		this.specialComplaint = specialComplaint;
	}
	
	public void salvar(){
		try{
			this.specialComplaint.setDataParecer(new Date());
			this.specialComplaint.setDataQueixa(new Date());
			this.specialComplaint.setSituacao(Situacao.OPEN);
			specialComplaintService.inserir(specialComplaint);
			facesContext.addMessage(null, 
									new FacesMessage(FacesMessage.SEVERITY_INFO, 
													 "Registrado!", 
													 "Registro bem sucedido."));
			init();
		} catch(Exception e){
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Não foi possível registrar a reclamação!", "Registration mal sucedido"));			
		}
	}

}

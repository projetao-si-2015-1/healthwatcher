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
import br.cin.ufpe.healthwatcher.model.Employee;
import br.cin.ufpe.healthwatcher.model.Situacao;
import br.cin.ufpe.healthwatcher.model.SpecialComplaint;
import br.cin.ufpe.healthwatcher.service.SpecialComplaintService;

@ManagedBean
@ViewScoped
public class SpecialComplaintView implements Serializable {
	
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
	
	public String salvar(){
		try{
			this.specialComplaint.setDataParecer(new Date());
			this.specialComplaint.setDataQueixa(new Date());
			this.specialComplaint.setSituacao(Situacao.OPEN);
			specialComplaintService.inserir(specialComplaint);
			facesContext.getExternalContext().getFlash().put("codigo", specialComplaint.getCodigo());
			return "specialComplaintInserted?faces-redirect=true";
		} catch(Exception e){
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Não foi possível registrar a reclamação!", "Registration mal sucedido"));
            return "";
		}
	}

}

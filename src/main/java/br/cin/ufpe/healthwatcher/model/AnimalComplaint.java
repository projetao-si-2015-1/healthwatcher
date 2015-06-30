package br.cin.ufpe.healthwatcher.model;

import java.io.Serializable;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: AnimalComplaint
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name="animalComplaintByCode", query="SELECT a FROM AnimalComplaint a WHERE a.codigo = :code"),
	@NamedQuery(name="allAnimalComplaints", query="SELECT a FROM AnimalComplaint a"),
	@NamedQuery(name="animalComplaintsBySituation", query="SELECT a FROM AnimalComplaint a WHERE a.situacao = :situacao"),
})
public class AnimalComplaint extends Complaint implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Short animalQuantity;
	
	@Temporal(TemporalType.DATE)
	private Date inconvenienceDate;
	
	@Column(length = 100)
	private String animal;
	
	// TODO: Verificar se a anotacao esta correta
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "code")
	private Address occurenceLocalAddress;
	
	public AnimalComplaint() {
		super();
	}   
	public Short getAnimalQuantity() {
		return this.animalQuantity;
	}

	public void setAnimalQuantity(Short animalQuantity) {
		this.animalQuantity = animalQuantity;
	}   
	public Date getInconvenienceDate() {
		return this.inconvenienceDate;
	}

	public void setInconvenienceDate(Date inconvenienceDate) {
		this.inconvenienceDate = inconvenienceDate;
	}   
	public String getAnimal() {
		return this.animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}   
	public Address getOccurenceLocalAddress() {
		return this.occurenceLocalAddress;
	}

	public void setOccurenceLocalAddress(Address occurenceLocalAddress) {
		this.occurenceLocalAddress = occurenceLocalAddress;
	}
   
}

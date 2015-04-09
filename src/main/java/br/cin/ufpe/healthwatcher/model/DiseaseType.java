package br.cin.ufpe.healthwatcher.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: DiseaseType
 *
 */
@Entity

public class DiseaseType implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	private Integer code;
	
	@Column(length = 100)
	private String name;
	
	@Column(length = 200)
	private String description;
	
	@Column(length = 100)
	private String manifestation;
	
	@Column(length = 100)
	private String duration;
	
	@ManyToMany
	@JoinTable
	(name = "DiseaseType_symptom", joinColumns = @JoinColumn(name = "DiseaseType_code"), inverseJoinColumns = @JoinColumn(name = "symptom_code"))
	private List<Symptom> symptoms;

	// Getters and Setters
	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   
	public String getManifestation() {
		return this.manifestation;
	}

	public void setManifestation(String manifestation) {
		this.manifestation = manifestation;
	}   
	public String getDuration() {
		return this.duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}   
	public List<Symptom> getSymptoms() {
		return this.symptoms;
	}

	public void setSymptoms(List<Symptom> symptoms) {
		this.symptoms = symptoms;
	}
   
}

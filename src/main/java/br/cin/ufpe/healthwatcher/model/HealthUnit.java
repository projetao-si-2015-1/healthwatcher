package br.cin.ufpe.healthwatcher.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.List;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: HealthUnit
 * @version 1.0
 * @date 2015-04-05 12:20:00
 * @author m_rocha
 */
@Entity

public class HealthUnit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer code;

	@Column(length = 200)
	private String description;

	@ManyToMany
	@JoinTable
	(name = "healthunit_medicalspeciality", joinColumns = @JoinColumn(name = "healthunit_code"), inverseJoinColumns = @JoinColumn(name = "medicalspeciality_code"))
	private List<MedicalSpeciality> specialities;

	// Getters and Setters
	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   
	public List<MedicalSpeciality> getSpecialities() {
		return this.specialities;
	}

	public void setSpecialities(List<MedicalSpeciality> specialities) {
		this.specialities = specialities;
	}

}

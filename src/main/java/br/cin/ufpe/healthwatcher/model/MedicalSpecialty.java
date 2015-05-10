package br.cin.ufpe.healthwatcher.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: MedicalSpeciality
 * @version 1.0
 * @date 2015-04-05 12:05:00
 * @author m_rocha
 */

@Entity
@NamedQueries({
	@NamedQuery(name="medicalSpecialtyByCode", query="SELECT m FROM MedicalSpecialty m WHERE m.code = :code"),
	@NamedQuery(name="listAllMedicalSpecialties", query="SELECT m FROM MedicalSpecialty m")
})
public class MedicalSpecialty implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	private Integer code;
	
	@Column(length = 200)
	private String description;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicalSpecialty other = (MedicalSpecialty) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MedicalSpeciality [code=" + code + ", description="	+ description + "]";
	}
   
}

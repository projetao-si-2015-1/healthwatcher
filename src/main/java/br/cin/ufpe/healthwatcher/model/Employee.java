package br.cin.ufpe.healthwatcher.model;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Employee
 * @version 1.0
 * @date 2015-04-05 11:58:00
 * @author m_rocha
 */
@Entity

public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length = 100)
	private String name;   

	@Id
	@Column(length = 100)
	private String login;

	@Column(length = 32)
	private String password;

	// To do: verificar se esse metodo sera feito no bean.
	public boolean validatePassword(String password) {
		return this.password.equals(password);
	}

	// Getters and Setters
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

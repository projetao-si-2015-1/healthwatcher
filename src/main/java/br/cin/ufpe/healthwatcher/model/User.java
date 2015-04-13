package br.cin.ufpe.healthwatcher.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	private String username;
	
	@Column(length=60)
	private String password;
	
	private boolean enable;
	
	private boolean administrador;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable(name="user_authorization",
			   joinColumns=@JoinColumn(name="username"),
			   inverseJoinColumns=@JoinColumn(name="nome"))	
	private List<Authorization> autorizacoes;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public List<Authorization> getAutorizacoes() {
		return autorizacoes;
	}

	public void setAutorizacoes(List<Authorization> autorizacoes) {
		this.autorizacoes = autorizacoes;
	}

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}


}

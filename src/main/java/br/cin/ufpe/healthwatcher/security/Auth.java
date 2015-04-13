package br.cin.ufpe.healthwatcher.security;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.cin.ufpe.healthwatcher.model.Employee;

@ManagedBean
@ViewScoped
public class Auth {

    private Employee employee;

	public Employee getEmployee() {
		return employee;
	}
}

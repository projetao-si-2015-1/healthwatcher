package br.cin.ufpe.healthwatcher.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.cin.ufpe.healthwatcher.model.User;

@ManagedBean
@SessionScoped
public class UserController {
	
	private User user;
	
	public UserController(){
		this.user = new User();
		SecurityContext context = SecurityContextHolder.getContext();
		if(context instanceof SecurityContext){
			Authentication authentication = context.getAuthentication();
			if(authentication instanceof Authentication){
				user.setUsername( ((User) authentication.getPrincipal()).getUsername());
			}
		}		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

package com.Prueba.ApiRest.dto;

import org.springframework.lang.NonNull;

public class LoginUsuario {
	@NonNull
	private String username;
	@NonNull
	private String password;
	
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
	
	
}

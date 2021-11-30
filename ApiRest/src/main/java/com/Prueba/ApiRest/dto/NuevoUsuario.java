package com.Prueba.ApiRest.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.lang.NonNull;

public class NuevoUsuario {
	@NonNull
	private String apellido;
	@NonNull
	private String nombre;
	@NonNull
	private String dni;
	@NonNull
	private String email;
	@NonNull
	private String username;
	@NonNull
	private String password;
	
	private Set<String> roles = new HashSet<>();
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
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
	public Set<String> getRoles() {
		return roles;
	}
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	} 

	
}

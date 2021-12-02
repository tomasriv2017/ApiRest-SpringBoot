package com.Prueba.ApiRest.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {
	@NonNull
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NonNull
	private String dni;
	@NonNull
	private String apellido;
	@NonNull
	private String nombre;
	@NonNull
	private String email;
	@NonNull
	private String username;
	@NonNull
	private String password;
	@NonNull
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name="idUsuario"), 
	inverseJoinColumns = @JoinColumn(name="idRol"))
	private Set<Rol> roles = new HashSet<>();
	@NonNull
	@CreationTimestamp
	private LocalDateTime fechaCreacion;
	@NonNull
	@UpdateTimestamp
	private LocalDateTime fechaActualizacion;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
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
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public LocalDateTime getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	
	public Set<Rol> getRoles() {
		return roles;
	}
	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	public Usuario() {
		super();
	}
	public Usuario(String dni, String apellido, String nombre, String email, String username, String password) {
		super();
		this.dni = dni;
		this.apellido = apellido;
		this.nombre = nombre;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	
	
}

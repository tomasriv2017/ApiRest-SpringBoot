package com.Prueba.ApiRest.models;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.Prueba.ApiRest.enums.Roles;

@Entity
@Table(name = "rol")
public class Rol {
	@NonNull
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NonNull
	@Enumerated(EnumType.STRING)
	private Roles tipo; 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Roles getTipo() {
		return tipo;
	}
	public void setTipo(Roles tipo) {
		this.tipo = tipo;
	}

	public Rol() {
		super();
	}
	public Rol(Roles tipo) {
		super();
		this.tipo = tipo;
	}
	
	
}

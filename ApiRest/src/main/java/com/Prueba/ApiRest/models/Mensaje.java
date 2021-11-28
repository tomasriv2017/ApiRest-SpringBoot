package com.Prueba.ApiRest.models;

import org.springframework.lang.NonNull;

public class Mensaje {
	@NonNull
	private String mensaje;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Mensaje(String mensaje) {
		super();
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "Mensaje [mensaje=" + mensaje + "]";
	}
	
	
}

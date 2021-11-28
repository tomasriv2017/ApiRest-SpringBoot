package com.Prueba.ApiRest.services;

import java.util.Optional;

import com.Prueba.ApiRest.models.Rol;

public interface IRolService {
	
	Optional<Rol> findById(int id);
	Optional<Rol> findByTipo(String tipo);
}

package com.Prueba.ApiRest.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Prueba.ApiRest.enums.Roles;
import com.Prueba.ApiRest.models.Rol;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Serializable> {
	
	@Query(value = "SELECT r from Rol r where r.tipo = (:tipo)")
	public Optional<Rol>findByTipo(Roles tipo);
}

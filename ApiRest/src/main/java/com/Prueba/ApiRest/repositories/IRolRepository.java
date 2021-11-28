package com.Prueba.ApiRest.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Prueba.ApiRest.models.Rol;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Serializable> {
	Optional<Rol>findByTipo(String tipo);
}

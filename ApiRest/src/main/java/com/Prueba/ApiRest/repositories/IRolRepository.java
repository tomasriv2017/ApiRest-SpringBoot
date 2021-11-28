package com.Prueba.ApiRest.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Prueba.ApiRest.models.Rol;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Serializable> {
	
}

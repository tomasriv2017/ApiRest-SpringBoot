package com.Prueba.ApiRest.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Prueba.ApiRest.models.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Serializable>{
	
	Optional<Usuario> findByUsername(String username);
	Optional<Usuario> findByEmail(String email);
}

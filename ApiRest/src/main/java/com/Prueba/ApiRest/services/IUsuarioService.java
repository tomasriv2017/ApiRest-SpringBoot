package com.Prueba.ApiRest.services;

import java.util.List;
import java.util.Optional;

import com.Prueba.ApiRest.models.Usuario;

public interface IUsuarioService {
	Optional<Usuario> findById(int id);
	Optional<Usuario> findByUsername(String username);
	Optional<Usuario> findByEmail(String email);
	List<Usuario> findAll();
	Usuario saveOrUpdate(Usuario usuario);
	void delete(Usuario usuario) throws Exception;

}

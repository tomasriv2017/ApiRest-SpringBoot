package com.Prueba.ApiRest.servicesImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Prueba.ApiRest.enums.Roles;
import com.Prueba.ApiRest.models.Rol;
import com.Prueba.ApiRest.repositories.IRolRepository;
import com.Prueba.ApiRest.services.IRolService;

@Service
public class RolService implements IRolService{

	@Autowired
	private IRolRepository rolRepository;
	
	@Override
	public Optional<Rol> findById(int id) {
		// TODO Auto-generated method stub
		return rolRepository.findById(id);
	}

	@Override
	public Optional<Rol> findByTipo(Roles rolTipo) {
		// TODO Auto-generated method stub
		return rolRepository.findByTipo(rolTipo);
	}
	
}

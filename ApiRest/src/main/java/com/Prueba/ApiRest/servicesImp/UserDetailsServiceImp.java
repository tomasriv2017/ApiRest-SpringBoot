package com.Prueba.ApiRest.servicesImp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Prueba.ApiRest.models.Usuario;
import com.Prueba.ApiRest.models.UsuarioPrincipal;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Usuario usuario = usuarioService.findByUsername(username).get();
		return UsuarioPrincipal.build(usuario);
	}
	
	

}

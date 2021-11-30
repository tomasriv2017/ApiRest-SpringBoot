package com.Prueba.ApiRest.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Prueba.ApiRest.dto.JwtDto;
import com.Prueba.ApiRest.dto.LoginUsuario;
import com.Prueba.ApiRest.dto.NuevoUsuario;
import com.Prueba.ApiRest.enums.Roles;
import com.Prueba.ApiRest.jwt.JwtProvider;
import com.Prueba.ApiRest.models.Mensaje;
import com.Prueba.ApiRest.models.Rol;
import com.Prueba.ApiRest.models.Usuario;
import com.Prueba.ApiRest.servicesImp.RolService;
import com.Prueba.ApiRest.servicesImp.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private RolService rolService;
	
	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/register")
	public ResponseEntity<?> registarUsuario(@Validated @RequestBody NuevoUsuario nuevoUsuario ){
		
		if(usuarioService.findByUsername(nuevoUsuario.getUsername()).isEmpty() &&
				usuarioService.findByEmail(nuevoUsuario.getEmail()).isEmpty()) {
			
			Usuario usuario = new Usuario(nuevoUsuario.getDni(), nuevoUsuario.getApellido(), 
					nuevoUsuario.getNombre(), nuevoUsuario.getEmail(), nuevoUsuario.getUsername(), passwordEncoder.encode(nuevoUsuario.getPassword()) );
			Set<Rol> roles = new HashSet<>();
		    roles.add(rolService.findByTipo(Roles.TIPO_USER).get());//por defecto se le agregar el rol user
			if(nuevoUsuario.getRoles().contains(Roles.TIPO_ADMIN.toString()) ) {
				roles.add( rolService.findByTipo(Roles.TIPO_ADMIN).get());
			}
			System.out.println(roles.size());
			usuario.setRoles(roles);
			return new ResponseEntity<Usuario>(usuarioService.saveOrUpdate(usuario), HttpStatus.CREATED);	
			
		}else {
			return new ResponseEntity<Mensaje>(new Mensaje("El email o username ya existen"), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUsuario(@Validated @RequestBody LoginUsuario loginUsuario, BindingResult bindingResults){
		if(bindingResults.hasErrors()) {
			return new ResponseEntity<Mensaje>(new Mensaje("Campos invalidos"), HttpStatus.BAD_REQUEST);
		}
		if(!usuarioService.findByUsername(loginUsuario.getUsername()).isEmpty() ) {
			//UsuarioPrincipal usuarioPrincial =( (UsuarioPrincipal)auth.getPrincipal()); //obtengo al usuario que se va a loguear
			Authentication auth = 
					authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getUsername(),
							loginUsuario.getPassword())); //autenticamos al user que se va a loguear
			SecurityContextHolder.getContext().setAuthentication(auth); //se lo pasamos al contexto de autenticacion para que tenga en cuenta los "privilegios" de este usuario
			
			String token  = jwtProvider.generateToken(auth);//genero el token
			JwtDto jwtDto = new JwtDto(token, jwtProvider.getUsernameFromToken(token), auth.getAuthorities());
			return new ResponseEntity<JwtDto>(jwtDto, HttpStatus.OK);
			
		}else return new ResponseEntity<Mensaje>(new Mensaje("No existe un usuario con ese username"), HttpStatus.BAD_REQUEST);
		
	}
	
	
}

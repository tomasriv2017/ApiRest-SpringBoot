package com.Prueba.ApiRest.controllers;

import com.Prueba.ApiRest.models.Mensaje;
import com.Prueba.ApiRest.models.Usuario;
import com.Prueba.ApiRest.servicesImp.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizarUsuario(@RequestBody Usuario usuarioModificado, 
			@PathVariable(name = "id") int id){
		if(!usuarioService.findById(id).isPresent()) {
			return new ResponseEntity<Mensaje>(new Mensaje("Este usuario no existe"), HttpStatus.BAD_REQUEST);
		}else {
			usuarioModificado.setId(id);
			return new ResponseEntity<Usuario>(usuarioService.saveOrUpdate(usuarioModificado), HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminarUsuario(@PathVariable("id")int id) throws Exception{
		Optional<Usuario> usuarioBuscado = usuarioService.findById(id);
		if(!usuarioBuscado.isPresent()) {
			return new ResponseEntity<Mensaje>(new Mensaje("Este usuario no existe"), HttpStatus.NOT_FOUND);
		}else {
			usuarioService.delete(usuarioBuscado.get());
			return new ResponseEntity<Mensaje>(new Mensaje("El usuario con id="+id+" fue eliminado"), HttpStatus.OK);
		}
	}
	
	@GetMapping("/")
	public List<Usuario> traerTodos(){
		return usuarioService.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> traerUsuario(@PathVariable(name="id") int id){		
		Optional<Usuario> usuarioBuscado = usuarioService.findById(id);
		if(!usuarioBuscado.isPresent()) {
			return new ResponseEntity<Mensaje>(new Mensaje("El usuario buscado no existe"), HttpStatus.NOT_FOUND);
		} else return new ResponseEntity<Usuario>(usuarioBuscado.get(), HttpStatus.FOUND);
	}
 
	
}

package com.Prueba.ApiRest.servicesImp;

import com.Prueba.ApiRest.models.Usuario;
import com.Prueba.ApiRest.repositories.IUsuarioRepository;
import com.Prueba.ApiRest.services.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Override
	public Optional<Usuario> findById(int id) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id);
	}

	@Override
	public Optional<Usuario> findByUsername(String username) {
		// TODO Auto-generated method stub
		return usuarioRepository.findByUsername(username);
	}

	@Override
	public Optional<Usuario> findByEmail(String email) {
		// TODO Auto-generated method stub
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario saveOrUpdate(Usuario usuario) {
		// TODO Auto-generated method stub
		int idUser = usuario.getId();
		Optional<Usuario> user = usuarioRepository.findById(idUser);
		if( !user.isPresent() ) {
			return usuarioRepository.save(usuario);
		}else {
			map(usuario, user.get());
			return usuarioRepository.save( user.get());
		}
	}

	@Override
	public void delete(Usuario usuario) throws Exception{
		try {
			usuarioRepository.delete(usuario);
		}catch (Exception e) {
			// TODO: handle exception
			throw new Exception("Ocurrio un error --> "+e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param from (Usuario modificado)
	 * @param to (Usuario guardado en la bd)
	 */
	
	void map(Usuario from, Usuario to) {
		if(!to.getApellido().isEmpty()) to.setApellido(from.getApellido());
		if(!to.getNombre().isEmpty())to.setNombre(from.getNombre());
		if(!to.getDni().isEmpty())to.setDni(from.getDni());
		if(!to.getEmail().isEmpty())to.setEmail(from.getEmail());
		if(!to.getUsername().isEmpty())to.setUsername(from.getUsername());
		if(!to.getPassword().isEmpty())to.setPassword(from.getPassword());
	}

}

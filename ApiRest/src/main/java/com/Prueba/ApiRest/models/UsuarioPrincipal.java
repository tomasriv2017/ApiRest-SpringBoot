package com.Prueba.ApiRest.models;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioPrincipal implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String email;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	public String getEmail() {
		return email;
	}
	public int getId() {
		return id;
	}
	
	public static UsuarioPrincipal build(Usuario usuario) {
		List<GrantedAuthority> authorities = usuario.getRoles().stream().map(rol->
		new SimpleGrantedAuthority(rol.getTipo().name())).collect(Collectors.toList());
		
		return new UsuarioPrincipal(usuario.getId(),usuario.getEmail(), usuario.getUsername(), usuario.getPassword(), authorities);
	}
	
	public UsuarioPrincipal() {
		super();
	}
	public UsuarioPrincipal(int id, String email, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}
	
	
	
}

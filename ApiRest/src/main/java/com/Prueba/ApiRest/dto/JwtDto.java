package com.Prueba.ApiRest.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtDto {
	private String token;
	private final String bearer = "Bearer";
	private int id;
	private String username;
	private Collection<? extends GrantedAuthority> authorities;
	
	public JwtDto(String token, int id , String username, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.token = token;
		this.id = id;
		this.username = username;
		this.authorities = authorities;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public String getBearer() {
		return bearer;
	}
	
	
	
}

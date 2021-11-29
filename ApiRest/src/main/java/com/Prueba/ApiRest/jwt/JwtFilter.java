package com.Prueba.ApiRest.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Prueba.ApiRest.servicesImp.UserDetailsServiceImp;

public class JwtFilter extends OncePerRequestFilter {
	private final static Logger logger = LoggerFactory.getLogger(JwtFilter.class); //para controlar errores en fase de desarrollo

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private UserDetailsServiceImp userDetailsServiceImp;
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String token = getToken(req); //obtengo el token
			if(!token.isEmpty() && jwtProvider.validateToken(token)){ //valido el token
				String username = jwtProvider.getUsernameFromToken(token); //obtengo el username mediante el token
				UserDetails userDetails = userDetailsServiceImp.loadUserByUsername(username); //obtengo el usuario mediante el username
				UsernamePasswordAuthenticationToken auth = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); //autenticamos al usuario
				SecurityContextHolder.getContext().setAuthentication(auth); //se lo pasamos al contexto de autenticacion para que tenga en cuenta los "privilegios" de este usuario
			}
		}catch (Exception e) {
			// TODO: handle exception
			logger.error("Error en el metodo doFilter");
		}
		filterChain.doFilter(req, res); //repito el proceso por cada endpoint que lo requiera
	}
	
	private String getToken(HttpServletRequest req) {
		String header = req.getHeader("Authorization"); //obtengo el token que se encuentra en la cabecera 
		System.out.println("Header --> "+header);
		if(!header.isEmpty() && header.startsWith("Bearer")) { //si la cabecera no esta vacia y empieza con Bearer ("un estandar de JWT para los tokens")
			header =  header.split(" ")[1]; //retiro "Bearer" y me quedo como todo lo demas
			System.out.println("Header modificado --> "+header);
		}
		return header;
	}
	
}
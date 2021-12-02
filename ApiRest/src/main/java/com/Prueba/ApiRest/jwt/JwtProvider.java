package com.Prueba.ApiRest.jwt;

import com.Prueba.ApiRest.models.UsuarioPrincipal;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
	private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);//para controlar errores en fase de desarrollo

	@Value("${jwt.secret}") //palabra secreta con la que se realizar la fima digital, definida en application.properties
	private String secret;

	@Value("${jwt.expiration}") //tiempo de vida del token, definido en application.properties
	private int expiration;


	public String generateToken(Authentication auth) {
		UsuarioPrincipal usuarioPrincial =( (UsuarioPrincipal)auth.getPrincipal()); //obtengo al usuario que se va a loguear
		return Jwts.builder() //creo el token
				.setIssuedAt(new Date()) //fecha de creacion del token
				.setId( String.valueOf(usuarioPrincial.getId()) ) //id de user que genero el token al logguearse
				.setSubject(usuarioPrincial.getUsername()) //username del user que genero el token al loguearse
				.setExpiration(new Date( new Date().getTime() + this.expiration * 1000)) //tiempo que va a durar el token
				.signWith(SignatureAlgorithm.HS512, this.secret) //algoritmo con el que se realiza la firma y la clave secreta para la firma digital
				.compact();
	}

	public String getUsernameFromToken(String token) {
		return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody().getSubject(); //obtengo el username una vez desencriptado el token con la firma digital
	}

	public String getIdFromToken(String token) {
		return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody().getId(); //obtengo el id del user una vez desencriptado el token con la firma digital
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token); //valido el token con su firma digital
			return true;
		}catch (MalformedJwtException e) {
			// TODO: handle exception
			logger.error("Token mal formado");
		}catch (ExpiredJwtException e) {
			// TODO: handle exception
			logger.error("Token expirado");
		}catch (UnsupportedJwtException e) {
			// TODO: handle exception
			logger.error("Token no soportado");
		}catch (SignatureException e) {
			// TODO: handle exception
			logger.error("Error en la firma del token");
		}catch (IllegalArgumentException e) {
			// TODO: handle exception
			logger.error("Token vacio o con argumentos no validos");
		}
		return false;
	}

}
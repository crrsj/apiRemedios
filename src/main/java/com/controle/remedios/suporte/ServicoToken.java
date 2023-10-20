package com.controle.remedios.suporte;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.controle.remedios.usuarios.Usuario;

@Service
public class ServicoToken {
	@Value("${api.seguranca.token.secreto}")
    private String secreto;
	
	public String gerarToken(Usuario usuario) {
	
		try {
		   var algorithm =  Algorithm.HMAC256(secreto);
		    return JWT.create()
		        .withIssuer("API_Remedios")
		        .withSubject(usuario.getLogin())
		        .withExpiresAt(dataExpiracao())
     		    .sign(algorithm);
		} catch (JWTCreationException exception){
			    throw new RuntimeException("Erro ao gerar token",exception);
		    
		}
	}
	
	public String getSubject(String TokenJWT) {
		try {
			 var algorithm =  Algorithm.HMAC256(secreto);
		    return JWT.require(algorithm)
		  	.withIssuer("API_Remedios")		      	      
		    .build()
		    .verify(TokenJWT)
		    .getSubject(); 
		   
		} catch (JWTVerificationException exception){
		       throw new RuntimeException("Token inválido ou exoirado !");
		}
		
	}
	
	private Instant dataExpiracao() {
		
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
	
	
}

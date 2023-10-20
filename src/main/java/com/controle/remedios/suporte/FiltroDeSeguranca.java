package com.controle.remedios.suporte;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.controle.remedios.usuarios.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FiltroDeSeguranca extends OncePerRequestFilter {
	@Autowired
	private ServicoToken servicoToken;
	@Autowired
	private UsuarioRepository usuarioRepositoru;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		    var tokenJWT = recuperarToken(request);
		 
		    if(tokenJWT != null) {
		    var subject = servicoToken.getSubject((String) tokenJWT);
		    var usuario = usuarioRepositoru.findByLogin(subject);
		    
		    var authentication = new UsernamePasswordAuthenticationToken(usuario, null,usuario.getAuthorities());
		    
		    SecurityContextHolder.getContext().setAuthentication(authentication);
		    }
		    filterChain.doFilter(request, response);
	    
	}

	private Object recuperarToken(HttpServletRequest request) {
		var authorizationHeader = request.getHeader("Authorization");
		if(authorizationHeader != null) {
			return authorizationHeader.replace("Bearer ", "");
			
		}
		
		return null;
		
	}

}

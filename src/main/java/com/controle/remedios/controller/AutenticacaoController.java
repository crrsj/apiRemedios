package com.controle.remedios.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controle.remedios.suporte.DadosTokenJWT;
import com.controle.remedios.suporte.ServicoToken;
import com.controle.remedios.usuarios.DadosAutenticacao;
import com.controle.remedios.usuarios.Usuario;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
	@Autowired
	private AuthenticationManager manager;
	@Autowired
	private ServicoToken servicoToken;
	
    @PostMapping
	public ResponseEntity<?>efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
    var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
	var autenticacao = manager.authenticate(token);
	var tokenJWT =  servicoToken.gerarToken((Usuario)autenticacao.getPrincipal());
	return ResponseEntity.ok( new DadosTokenJWT(tokenJWT));
	}

}

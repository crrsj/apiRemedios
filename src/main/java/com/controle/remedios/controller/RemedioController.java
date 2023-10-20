package com.controle.remedios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.controle.remedios.suporte.DadosAtualizarRemedio;
import com.controle.remedios.suporte.DadosCadastroRemedio;
import com.controle.remedios.suporte.DadosDetalhamentoRemedio;
import com.controle.remedios.suporte.DadosListagemRemedio;
import com.controle.remedios.suporte.Remedio;
import com.controle.remedios.suporte.RemedioRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/remedio")
public class RemedioController {
	@Autowired
	private RemedioRepository remedioRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoRemedio> cadastrar(@RequestBody @Valid DadosCadastroRemedio dados,UriComponentsBuilder uriBuilder) {
		var remedio = new Remedio(dados);
		remedioRepository.save(remedio);
		var uri = uriBuilder.path("/remedio/{id}").buildAndExpand(remedio.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoRemedio(remedio));
		
	}
	
	@GetMapping
	public ResponseEntity<List<DadosListagemRemedio>>listar(){
	 var lista = remedioRepository.findAllByAtivoTrue().stream().map(DadosListagemRemedio::new).toList();
	 return ResponseEntity.ok(lista);
	}
	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoRemedio> atualizar(@RequestBody @Valid DadosAtualizarRemedio dados) {
		
		var remedio = remedioRepository.getReferenceById(dados.id());
		remedio.atualizarInformacoes(dados);
		return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio)); 
		
	}

	@DeleteMapping("/{id}")	
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		remedioRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	
	}
	
	

	@GetMapping("/{id}")	
	@Transactional
	public ResponseEntity<DadosDetalhamentoRemedio>detalhar(@PathVariable Long id) {
		var remedio = remedioRepository.getReferenceById(id);
		
		return ResponseEntity.ok(new DadosDetalhamentoRemedio(remedio));
	
	}
	
	@DeleteMapping("/inativar/{id}")
	@Transactional
	public ResponseEntity<Void> inativar(@PathVariable Long id) {
		var remedio = remedioRepository.getReferenceById(id);
		remedio.inativar();
		return ResponseEntity.noContent().build();
	}
	@PutMapping("/reativar/{id}")
	@Transactional
	public ResponseEntity<Void> Reativar(@PathVariable Long id) {
		var remedio = remedioRepository.getReferenceById(id);
		remedio.reativar();
		return ResponseEntity.noContent().build();
	}
}

package com.controle.remedios.erros;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratarErros {
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> tratadorErros404 () {			
		return ResponseEntity.notFound().build();
		
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?>tratadorErros400(MethodArgumentNotValidException ex) {
		var erros = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(erros.stream().map(DadosErros::new).toList());
	}

	 public record DadosErros(String campo,String message) {
		 public DadosErros(FieldError erro) {
			 this(erro.getField(),erro.getDefaultMessage());
		 }
	 }
	
}
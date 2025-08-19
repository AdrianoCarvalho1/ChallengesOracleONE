package com.br.api.forum_hub.config.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TopicoComInformacaoFaltandoException {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
		var exceptionErro = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(exceptionErro.stream().map(DadosErroValidacao::new).toList());
	}
	
	private record DadosErroValidacao(String campo, String mesagem) {
		public DadosErroValidacao(FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
	}

}

package com.br.api.forum_hub.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TopicoNaoEncontradoException extends RuntimeException {
	
	public TopicoNaoEncontradoException() {
		
	}
	
	public TopicoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

}

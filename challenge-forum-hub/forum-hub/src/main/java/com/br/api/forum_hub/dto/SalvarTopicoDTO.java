package com.br.api.forum_hub.dto;

import com.br.api.forum_hub.model.Topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SalvarTopicoDTO(
		@NotBlank(message = "Título é obrigatório")
		@Size(min = 5, max = 100, message = "Título deve ter entre 5 e 100 caracteres")
		String titulo,
		
		@NotBlank(message = "Mensagem é obrigatória")
		@Size(min = 10, message = "Mensagem deve ter no mínio 10 caracteres")
		String mensagem,
		@NotBlank(message = "Autor é obrigatório")
		String autor, 
		@NotBlank(message = "Curso é obrigatório")
		String curso) {
	
	public SalvarTopicoDTO(Topico topico) {
		this(
				topico.getTitulo(), 
				topico.getMensagem(), 
				topico.getAutor(), 
				topico.getCurso());
	}

}

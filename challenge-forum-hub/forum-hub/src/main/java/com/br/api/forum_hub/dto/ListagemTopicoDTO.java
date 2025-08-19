package com.br.api.forum_hub.dto;

import java.time.LocalDateTime;

import com.br.api.forum_hub.enums.Status;
import com.br.api.forum_hub.model.Topico;

public record ListagemTopicoDTO(
		
		Long id,
		String titulo,
		String mensagem,
		LocalDateTime dataCriacao,
		Status status,
		String autor,
		String curso) {
	
	public ListagemTopicoDTO(Topico topico) {
		this(
				topico.getId(),
				topico.getTitulo(), 
				topico.getMensagem(), 
				topico.getDataCriacao(), 
				topico.getStatus(), 
				topico.getAutor(), 
				topico.getCurso());
	}

}

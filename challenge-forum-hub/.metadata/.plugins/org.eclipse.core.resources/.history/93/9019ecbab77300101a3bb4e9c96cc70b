package com.br.api.forum_hub.config;

import org.springframework.stereotype.Component;

import com.br.api.forum_hub.dto.SalvarTopicoDTO;
import com.br.api.forum_hub.dto.TopicoResponseDTO;
import com.br.api.forum_hub.model.Topico;

@Component
public class ConversorDTO {
	
	public Topico toEntity(SalvarTopicoDTO salvarTopicoDTO) {
		Topico topico = new Topico();
		topico.setTitulo(salvarTopicoDTO.titulo());
		topico.setMensagem(salvarTopicoDTO.mensagem());
		topico.setAutor(salvarTopicoDTO.autor());
		topico.setCurso(salvarTopicoDTO.curso());
		return topico;
	}
	
	public TopicoResponseDTO toDto(Topico topico) {
		return new TopicoResponseDTO(topico);
	}

}

package com.br.api.forum_hub.config;

import org.springframework.stereotype.Component;

import com.br.api.forum_hub.dto.SalvarTopicoDTO;
import com.br.api.forum_hub.dto.TopicoResponseDTO;
import com.br.api.forum_hub.dto.UsuarioDTO;
import com.br.api.forum_hub.dto.UsuarioResponseDTO;
import com.br.api.forum_hub.model.Topico;
import com.br.api.forum_hub.model.Usuario;

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
	
	public Usuario toEntityUsuario(UsuarioDTO dto) {
		Usuario usuario = new Usuario();
		usuario.setNome(dto.nome());
		usuario.setEmail(dto.email());
		usuario.setSenha(dto.senha());
		return usuario;
	}
	
	public UsuarioResponseDTO toDtoUsuario(Usuario usuario) {
		return new UsuarioResponseDTO(usuario);
	}

}

package com.br.api.forum_hub.dto;

import com.br.api.forum_hub.model.Usuario;

public record UsuarioResponseDTO (
		Long id,
		String nome,
		String email,
		String senha
) {
	public UsuarioResponseDTO(Usuario usuario) {
		this(
			usuario.getId(),
			usuario.getNome(),
			usuario.getEmail(),
			usuario.getSenha());
	}
}

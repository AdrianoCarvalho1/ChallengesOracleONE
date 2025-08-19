package com.br.api.forum_hub.dto;

import com.br.api.forum_hub.model.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioDTO(
		@NotBlank(message = "Nome é obrigatório")
		@Size(min = 4, max = 10, message = "Nome deve ter entre 4 e 10 caracteres")
		String nome,
		
		@NotBlank(message = "Email é obrigatório")
		@Email(message = "Por favor entre com um email válido")
		String email,
		String senha) {
	public UsuarioDTO(Usuario usuario) {
		this(
				usuario.getNome(), 
				usuario.getEmail(), 
				usuario.getSenha());
	}
}

package com.br.api.forum_hub.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AutenticacaoDTO(
		
		@NotBlank(message = "Email é obrigatório")
		@Email(message = "Por favor entre com um email válido")
		String email,
		@NotBlank(message = "Senha é obrigatória")
		String senha) {

}

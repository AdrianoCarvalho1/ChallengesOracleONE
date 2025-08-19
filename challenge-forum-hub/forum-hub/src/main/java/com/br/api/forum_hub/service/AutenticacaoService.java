package com.br.api.forum_hub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.br.api.forum_hub.config.ConversorDTO;
import com.br.api.forum_hub.dto.UsuarioDTO;
import com.br.api.forum_hub.dto.UsuarioResponseDTO;
import com.br.api.forum_hub.model.Usuario;
import com.br.api.forum_hub.repository.IUsuarioRepository;

@Service
public class AutenticacaoService implements UserDetailsService{

	private final IUsuarioRepository repository;
	private final ConversorDTO conversorDTO;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public AutenticacaoService(IUsuarioRepository repository, ConversorDTO conversorDTO, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.conversorDTO = conversorDTO;
		this.passwordEncoder = passwordEncoder;
	}
	
	public UsuarioResponseDTO cadastrar(UsuarioDTO dto) {
		if(repository.existsByEmail(dto.email())) {
			throw new RuntimeException("Email já cadastrado");
		}
		Usuario usuario = conversorDTO.toEntityUsuario(dto);
		usuario.setSenha(passwordEncoder.encode(dto.senha()));
		Usuario usuarioCadastrado = repository.save(usuario);
		return conversorDTO.toDtoUsuario(usuarioCadastrado);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username){
		System.out.println("Tentando autenticar usuario: " + username);
		return repository.findByEmail(username)
				.orElseThrow(() -> {
					System.out.println("Usuário não encontrado");
					return new UsernameNotFoundException("Usuário não encontrado!");
				});
				
	}
	
}

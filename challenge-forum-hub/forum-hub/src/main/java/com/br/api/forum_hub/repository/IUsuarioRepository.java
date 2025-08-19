package com.br.api.forum_hub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.br.api.forum_hub.model.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long>{

	@EntityGraph(attributePaths = "perfis")
	Optional<Usuario> findByEmail(String email);

	boolean existsByEmail(String email);

}

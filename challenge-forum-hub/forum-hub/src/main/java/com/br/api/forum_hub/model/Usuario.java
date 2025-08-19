package com.br.api.forum_hub.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String senha;
	
	@ManyToMany
	@JoinTable(name = "usuario_perfil",
	joinColumns = @JoinColumn(name = "usuario_id"),
	inverseJoinColumns = @JoinColumn(name = "perfil_id"))
	private Set<Perfil> perfis = new HashSet<>();
	
	public Usuario() {
		super();
	}


	public Usuario(Long id, String nome, String email, String senha, Set<Perfil> perfis) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.perfis = perfis;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	public Set<Perfil> getPerfis() {
		return perfis;
	}


	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return perfis;
	}


	@Override
	public String getPassword() {
		return senha;
	}


	@Override
	public String getUsername() {
		return email;
	}
	
	
}

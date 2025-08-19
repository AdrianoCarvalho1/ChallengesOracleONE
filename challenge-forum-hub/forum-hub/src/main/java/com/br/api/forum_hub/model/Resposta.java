package com.br.api.forum_hub.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "resposta")
public class Resposta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String mensagem;
	@Column(name = "data_criacao", columnDefinition = "TIMESTAMP")
	private LocalDateTime dataCriacao = LocalDateTime.now();
	private String autor;
	private String solucao;
	@ManyToOne
	@JoinColumn(name = "topico_id")
	private Topico topico;
	
	public Resposta() {
	}

	public Resposta(Long id, String mensagem, LocalDateTime dataCriacao, String autor, String solucao, Topico topico) {
		this.id = id;
		this.mensagem = mensagem;
		this.dataCriacao = dataCriacao;
		this.autor = autor;
		this.solucao = solucao;
		this.topico = topico;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getSolucao() {
		return solucao;
	}

	public void setSolucao(String solucao) {
		this.solucao = solucao;
	}

	public Topico getTopico() {
		return topico;
	}

	public void setTopico(Topico topico) {
		this.topico = topico;
	}
	
	

}

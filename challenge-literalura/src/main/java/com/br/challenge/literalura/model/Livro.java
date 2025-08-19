package com.br.challenge.literalura.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "livros")
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String titulo;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "livro_autor",
			joinColumns = @JoinColumn(name = "livro_id"),
			inverseJoinColumns = @JoinColumn(name = "autor_id")
			)
	
	private List<AutorInfo> autores;
	@Enumerated(EnumType.STRING)
	private Languages languages;
	private Double downloads;
	
	public Livro() {
	}
	
	

	public Livro(List<LivroInfo> results) {
	}



	public Livro(String titulo, List<Autores> autoresDTO, List<String> languages, Double downloads) {
		this.titulo = titulo;
		this.autores = new ArrayList<>();
		this.languages = Languages.fromString(languages.get(0));
		this.downloads = downloads;
		
		for(Autores autorDTO : autoresDTO) {
			AutorInfo autor = new AutorInfo(
					autorDTO.name(), 
					autorDTO.anoNasc(),
					autorDTO.anoFale(),
					this
		);
		this.autores.add(autor);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<AutorInfo> getAutores() {
		return autores;
	}

	public void setAutores(List<AutorInfo> autores) {
		this.autores = autores;
	}

	public Languages getLanguages() {
		return languages;
	}

	public void setLanguages(Languages languages) {
		this.languages = languages;
	}

	public Double getDownloads() {
		return downloads;
	}

	public void setDownloads(Double downloads) {
		this.downloads = downloads;
	}

	@Override
	public String toString() {
		return "Informações Livro " + "\nTítulo: " + titulo + "\nAutores: " + autores + "\nNúmero downloads:" + downloads;
	}

	
	

	
}



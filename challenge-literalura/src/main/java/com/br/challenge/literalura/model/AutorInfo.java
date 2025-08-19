package com.br.challenge.literalura.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "autores")
public class AutorInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer anoNasc;
	private Integer anoFale;
	
	@ManyToMany(mappedBy = "autores")
	private List<Livro> livros;
	
	public AutorInfo() {
		this.livros = new ArrayList<>();
	};

	public AutorInfo(Long id, String name, Integer anoNasc, Integer anoFale, Livro livro) {
		this.id = id;
		this.name = name;
		this.anoNasc = anoNasc;
		this.anoFale = anoFale;
		this.livros = new ArrayList<>();
	}

	public AutorInfo(String name, Integer anoNasc, Integer anoFale, Livro livro) {
		super();
		this.name = name;
		this.anoNasc = anoNasc;
		this.anoFale = anoFale;
		this.livros = new ArrayList<>();
		if(livro != null) {
			this.livros.add(livro);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAnoNasc() {
		return anoNasc;
	}

	public void setAnoNasc(Integer anoNasc) {
		this.anoNasc = anoNasc;
	}

	public Integer getAnoFale() {
		return anoFale;
	}

	public void setAnoFale(Integer anoFale) {
		this.anoFale = anoFale;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	@Override
	public String toString() {
		return "\nNome:" + name + "\nAno Nascimento:" + anoNasc + "\nAno Falecimento:" + anoFale;
	}
	
	
	
	
}

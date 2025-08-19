package com.br.challenge.literalura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.challenge.literalura.model.AutorInfo;
import com.br.challenge.literalura.model.Languages;
import com.br.challenge.literalura.model.Livro;

public interface LivroRepository  extends JpaRepository<Livro, Long>{
	boolean existsByTitulo(String titulo);
	
	@Query("SELECT l FROM Livro l Where l.titulo ILIKE %:titulos%")
	List<Livro> findByTituloContaining(String titulo);
	
	@Query("SELECT a FROM Livro b JOIN b.autores a")
	List<AutorInfo> getAutorInfo();
	
	@Query("SELECT a FROM Livro b JOIN b.autores a WHERE a.anoNasc <= :ano AND (a.anoFale IS NULL OR a.anoFale >= :ano)")
	List<AutorInfo> getAutoresVivos(@Param("ano") Integer ano);

	List<Livro> findByLanguages(Languages languages);
	
}

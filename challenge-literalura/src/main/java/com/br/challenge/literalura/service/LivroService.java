package com.br.challenge.literalura.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.challenge.literalura.model.AutorInfo;
import com.br.challenge.literalura.model.Dados;
import com.br.challenge.literalura.model.Languages;
import com.br.challenge.literalura.model.Livro;
import com.br.challenge.literalura.model.LivroInfo;
import com.br.challenge.literalura.repository.LivroRepository;

@Service
public class LivroService {
	private ConsumoApi consumoApi = new ConsumoApi();
	private ConverteDados conversor = new ConverteDados();
	private LivroRepository repository;
	private final String BASE_URL = "https://gutendex.com/books/";
	private List<Livro> livros;
	
	@Autowired
	public LivroService(LivroRepository repository) {
		this.repository = repository;
	}
	

	public void obterLivroPorTitulo(String titulo) {
		try {
			String json = consumoApi.obterDados(BASE_URL + "?search=" + titulo.replace(" ", "%20"));
			
			Dados dados = conversor.obterDados(json, Dados.class);
			
			if(dados.results().isEmpty()) {
				System.out.println("Nenhum livro encontrado com esse título!");
				return;
			}
			
			for(LivroInfo livroInfo : dados.results()) {
				Livro livro = new Livro(
						livroInfo.titulo(),
						livroInfo.autores(),
						livroInfo.languages(),
						livroInfo.downloads()
						);
				repository.save(livro);
				System.out.println("Livro salvo: " + livro.getTitulo());
			}
		}catch(Exception e) {
			System.out.println("Erro ao buscar livro: " + e.getMessage());
		}
	}


	public void listarLivros() {
		List<Livro> livros = repository.findAll();
		if(livros.isEmpty()) {
			System.out.println("Nenhum livro registrado ainda!");
		}else {
			System.out.println("\n============ LIVROS REGISTRADOS ============");
			livros.forEach(l -> {
				System.out.println(l.toString());
				System.out.println();
			});
		}
		
	}


	public void listarLivrosPorAutor() {
		List<AutorInfo> autores = repository.getAutorInfo();
		System.out.println("\n============ AUTORES REGISTRADOS ============");
		autores.stream()
		.sorted(Comparator.comparing(AutorInfo::getName))
		.forEach(a -> System.out.printf("\nAutor: %s \nAno Nascimento: %s \nAno Falecimento: %s \n__________\n", 
				a.getName(), a.getAnoNasc(), a.getAnoFale()));		
	}


	public void listarAutoresVivosEmAno(int ano) {
		List<AutorInfo> autoresVivos = repository.getAutoresVivos(ano);
		
		if(autoresVivos.isEmpty()) {
			System.out.println("znNenhum autor vivo encontrado no ano: " + ano);
		}else {
			System.out.println("\n============ AUTORES VIVOS EM " + ano + " ============");
			autoresVivos.stream()
			.sorted(Comparator.comparing(AutorInfo::getName))
			.forEach(a -> System.out.printf("\nAutor: %s \nAno Nascimento: %s \nAno Falecimento: %s \n__________\n", 
					a.getName(), a.getAnoNasc(), a.getAnoFale() == null ? "Ainda vivo" : a.getAnoFale()));
		}
		
	}


	public String obterLivroPorIdioma(String languagesCode) {
		try {
			Languages language = Languages.fromString(languagesCode);
			List<Livro> livroIdioma = repository.findByLanguages(language);
			livroIdioma.stream()
				.forEach(System.out::println);
			return languagesCode;
		}catch(IllegalArgumentException e) {
			System.out.println("Idioma invalido: " + e.getMessage());
			return null;
		}catch(Exception e) {
			System.out.println("Idioma invalido: " + e.getMessage());
			return null;
		}
		
	}
}

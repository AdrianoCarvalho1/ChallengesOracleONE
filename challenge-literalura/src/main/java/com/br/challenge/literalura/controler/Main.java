package com.br.challenge.literalura.controler;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.challenge.literalura.model.Languages;
import com.br.challenge.literalura.service.LivroService;

@Component
public class Main {
	private Scanner scan = new Scanner(System.in);
	private final LivroService livroService;
	
	@Autowired
	public Main(LivroService livroService) {
		this.livroService = livroService;
	}
	
	public void showMenu() {
		var opcao = -1;
		do {
			var menu = """
                    1 - Buscar livro por título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros por idioma
                    0 - Sair
                    """;
			
			System.out.println(menu);
			System.out.println("Escolha uma opção: ");
			opcao = scan.nextInt();
			scan.nextLine();
			
			switch (opcao) {
			case 1: {
				System.out.println("Digite o título do livro: ");
				String titulo = scan.nextLine();
				livroService.obterLivroPorTitulo(titulo);
				break;
			}
			case 2:{
				livroService.listarLivros();
				break;
			}
			case 3: {
				livroService.listarLivrosPorAutor();
				break;
			}
			case 4: {
				System.out.println("Digite um ano para listar os autores vivos a partir dele");
				int ano = scan.nextInt();
				livroService.listarAutoresVivosEmAno(ano);
				break;
			}
			case 5: {
				String languages =
					"""
		             Idiomas disponveis:
		             %s
		             Escolha um idioma(digite o código):
					""".formatted(Arrays.stream(Languages.values())
							.map(l -> l.name() + " - " + l.getIdiomas())
							.collect(Collectors.joining("\n")));
				System.out.println(languages);
				languages = scan.nextLine();
				livroService.obterLivroPorIdioma(languages);
				break;
			}	
			case 0:{
				System.out.println("SAINDO...");
				break;
			}
			default:
				System.out.println("Opção inválida");
				break;
			}
		}while(opcao != 0);
		
		
	}
	

}

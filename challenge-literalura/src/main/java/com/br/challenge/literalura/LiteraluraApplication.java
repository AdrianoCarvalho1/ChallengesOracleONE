package com.br.challenge.literalura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.challenge.literalura.controler.Main;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	
	@Autowired
	private Main main;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		main.showMenu();
		
	}

}

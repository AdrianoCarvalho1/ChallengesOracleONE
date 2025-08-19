package com.br.challenge.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Autores (
		@JsonAlias("name") String name,
		@JsonAlias("birth_year") Integer anoNasc,
		@JsonAlias("death_year") Integer anoFale){

}

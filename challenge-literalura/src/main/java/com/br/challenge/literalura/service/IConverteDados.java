package com.br.challenge.literalura.service;

public interface IConverteDados{
	<T> T obterDados(String json, Class<T> classe);

}

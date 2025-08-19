package com.br.api.forum_hub.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.br.api.forum_hub.dto.ListagemTopicoDTO;
import com.br.api.forum_hub.dto.SalvarTopicoDTO;
import com.br.api.forum_hub.dto.TopicoResponseDTO;
import com.br.api.forum_hub.repository.ITopicoRepository;
import com.br.api.forum_hub.service.TopicoService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("topicos")
public class TopicoController {
	private final TopicoService topicoService;
	private final ITopicoRepository repository;
	
	public TopicoController(TopicoService topicoService, ITopicoRepository repository) {
		this.topicoService = topicoService;
		this.repository = repository;
	}
	
	@PostMapping("register-topic")
	@Transactional
	public ResponseEntity<TopicoResponseDTO> criarTopico(
			@Valid @RequestBody SalvarTopicoDTO salvarTopicoDTO, UriComponentsBuilder uriBuilder){
		
		TopicoResponseDTO topicoCriado = topicoService.salvarTopico(salvarTopicoDTO);
		
		var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topicoCriado.id());
		return ResponseEntity.created(uri.toUri()).body(topicoCriado);
	}

	@GetMapping("/listagem")
	public ResponseEntity<Page<ListagemTopicoDTO>> listarTopico(
			@PageableDefault(size = 10, sort = {"autor"}) Pageable paginacao){
		return ResponseEntity.ok(topicoService.listarTopico(paginacao));
	}
	
	@GetMapping("/listagem/{id}")
	public ResponseEntity<ListagemTopicoDTO> listarTopicoId(@PathVariable Long id){
		Optional<ListagemTopicoDTO> listagemDTO = topicoService.listarTopicoId(id);
		
		return listagemDTO.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/editar/{id}")
	@Transactional
	public ResponseEntity<TopicoResponseDTO> editarTopico(
			@PathVariable Long id,
			@Valid @RequestBody SalvarTopicoDTO salvarTopicoDTO){
		TopicoResponseDTO topicoAtualizado = topicoService.atualizarTopico(id, salvarTopicoDTO);
		return ResponseEntity.ok(topicoAtualizado);
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> excluir(@PathVariable Long id){
		topicoService.excluirTopico(id);
		return ResponseEntity.noContent().build();
		
	}
}

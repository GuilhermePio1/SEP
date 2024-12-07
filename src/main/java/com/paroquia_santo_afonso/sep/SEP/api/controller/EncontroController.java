package com.paroquia_santo_afonso.sep.SEP.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.paroquia_santo_afonso.sep.SEP.api.dto.ListarEncontroDTO;
import com.paroquia_santo_afonso.sep.SEP.api.dto.SalvarEncontroDTO;
import com.paroquia_santo_afonso.sep.SEP.api.dto.builder.EncontroBuilder;
import com.paroquia_santo_afonso.sep.SEP.domain.model.Encontro;
import com.paroquia_santo_afonso.sep.SEP.domain.service.EncontroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/encontros")
public class EncontroController {
	
	private final EncontroService encontroService;
	
	public EncontroController(EncontroService encontroService) {
		this.encontroService = encontroService;
	}

	@GetMapping
	public List<ListarEncontroDTO> listar() {
		return encontroService.listar().stream()
				.map(EncontroBuilder::toListarEncontroDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{encontroId}")
	public ResponseEntity<ListarEncontroDTO> buscar(@PathVariable Long encontroId) {
		return encontroService.buscar(encontroId)
				.map(encontro -> ResponseEntity.ok(EncontroBuilder.toListarEncontroDTO(encontro)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ListarEncontroDTO adicionar(@Valid @RequestBody SalvarEncontroDTO salvarEncontroDTO) {
		Encontro encontro = EncontroBuilder.toEncontro(salvarEncontroDTO);
		return EncontroBuilder.toListarEncontroDTO(encontroService.salvar(encontro));
	}
	
	@PutMapping("/{encontroId}")
	public ResponseEntity<ListarEncontroDTO> atualizar(@PathVariable Long encontroId, @Valid @RequestBody SalvarEncontroDTO salvarEncontroDTO) {
		if (!encontroService.existsById(encontroId)) {
			return ResponseEntity.notFound().build();
		}
		Encontro encontro = EncontroBuilder.toEncontro(salvarEncontroDTO);
		encontro.setId(encontroId);
		
		return ResponseEntity.ok(EncontroBuilder.toListarEncontroDTO(encontroService.salvar(encontro)));
	}
	
	@DeleteMapping("/{encontroId}")
	public ResponseEntity<Void> remover(@PathVariable Long encontroId) {
		if (!encontroService.existsById(encontroId)) {
			return ResponseEntity.notFound().build();
		}
		
		encontroService.excluir(encontroId);
		
		return ResponseEntity.noContent().build();
	}
	
}

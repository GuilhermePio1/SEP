package com.paroquia_santo_afonso.sep.SEP.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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

import com.paroquia_santo_afonso.sep.SEP.api.dto.EncontroDTO;
import com.paroquia_santo_afonso.sep.SEP.domain.model.Encontro;
import com.paroquia_santo_afonso.sep.SEP.domain.service.EncontroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/encontros")
public class EncontroController {
	
	private final EncontroService encontroService;
	
	private final ModelMapper modelMapper;
	
	public EncontroController(EncontroService encontroService, ModelMapper modelMapper) {
		this.encontroService = encontroService;
		this.modelMapper = modelMapper;
	}

	@GetMapping
	public List<EncontroDTO> listar() {
		return encontroService.listar().stream()
				.map(encontro -> modelMapper.map(encontro, EncontroDTO.class))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{encontroId}")
	public ResponseEntity<EncontroDTO> buscar(@PathVariable Long encontroId) {
		return encontroService.buscar(encontroId)
				.map(encontro -> ResponseEntity.ok(modelMapper.map(encontro, EncontroDTO.class)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EncontroDTO adicionar(@Valid @RequestBody EncontroDTO encontroDTO) {
		Encontro encontro = modelMapper.map(encontroDTO, Encontro.class);
		return modelMapper.map(encontroService.salvar(encontro), EncontroDTO.class);
	}
	
	@PutMapping("/{encontroId}")
	public ResponseEntity<EncontroDTO> atualizar(@PathVariable Long encontroId, @Valid @RequestBody EncontroDTO encontroDTO) {
		if (!encontroService.existsById(encontroId)) {
			return ResponseEntity.notFound().build();
		}
		Encontro encontro = modelMapper.map(encontroDTO, Encontro.class);
		encontro.setId(encontroId);
		
		return ResponseEntity.ok(modelMapper.map(encontroService.salvar(encontro), EncontroDTO.class));
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

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

import com.paroquia_santo_afonso.sep.SEP.api.dto.PastaDTO;
import com.paroquia_santo_afonso.sep.SEP.domain.model.Pasta;
import com.paroquia_santo_afonso.sep.SEP.domain.service.PastaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pastas")
public class PastaController {
	
	private final PastaService pastaService;
	
	private final ModelMapper modelMapper;
	
	public PastaController(PastaService pastaService, ModelMapper modelMapper) {
		this.pastaService = pastaService;
		this.modelMapper = modelMapper;
	}

	@GetMapping
	public List<PastaDTO> listar() {
		return pastaService.listar().stream()
				.map(pasta -> modelMapper.map(pasta, PastaDTO.class))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{pastaId}")
	public ResponseEntity<PastaDTO> buscar(@PathVariable Long pastaId) {
		return pastaService.buscar(pastaId)
				.map(pasta -> ResponseEntity.ok(modelMapper.map(pasta, PastaDTO.class)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PastaDTO adicionar(@Valid @RequestBody PastaDTO pastaDTO) {
		Pasta pasta = modelMapper.map(pastaDTO, Pasta.class);
		return modelMapper.map(pastaService.salvar(pasta), PastaDTO.class);
	}
	
	@PutMapping("/{pastaId}")
	public ResponseEntity<PastaDTO> atualizar(@PathVariable Long pastaId, @Valid @RequestBody PastaDTO pastaDTO) {
		if (!pastaService.existsById(pastaId)) {
			return ResponseEntity.notFound().build();
		}
		
		Pasta pasta = modelMapper.map(pastaDTO, Pasta.class);
		pasta.setId(pastaId);
		return ResponseEntity.ok(modelMapper.map(pastaService.salvar(pasta), PastaDTO.class));
	} 
	
	
	@DeleteMapping("/{pastaId}")
	public ResponseEntity<Void> remover(@PathVariable Long pastaId) {
		if (!pastaService.existsById(pastaId)) {
			return ResponseEntity.notFound().build();
		}
		
		pastaService.excluir(pastaId);
		
		return ResponseEntity.noContent().build();
	}

}

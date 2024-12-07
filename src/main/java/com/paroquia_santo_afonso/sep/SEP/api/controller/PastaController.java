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

import com.paroquia_santo_afonso.sep.SEP.api.dto.ListarPastaDTO;
import com.paroquia_santo_afonso.sep.SEP.api.dto.SalvarPastaDTO;
import com.paroquia_santo_afonso.sep.SEP.api.dto.builder.PastaBuilder;
import com.paroquia_santo_afonso.sep.SEP.domain.model.Pasta;
import com.paroquia_santo_afonso.sep.SEP.domain.service.PastaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pastas")
public class PastaController {
	
	private final PastaService pastaService;
	
	public PastaController(PastaService pastaService) {
		this.pastaService = pastaService;
	}

	@GetMapping
	public List<ListarPastaDTO> listar() {
		return pastaService.listar().stream()
				.map(pasta -> PastaBuilder.toListarPastaDTO(pasta))
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{pastaId}")
	public ResponseEntity<ListarPastaDTO> buscar(@PathVariable Long pastaId) {
		return pastaService.buscar(pastaId)
				.map(pasta -> ResponseEntity.ok(PastaBuilder.toListarPastaDTO(pasta)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ListarPastaDTO adicionar(@Valid @RequestBody SalvarPastaDTO pastaDTO) {
		Pasta pasta = PastaBuilder.toPasta(pastaDTO);
		return PastaBuilder.toListarPastaDTO(pastaService.salvar(pasta));
	}
	
	@PutMapping("/{pastaId}")
	public ResponseEntity<ListarPastaDTO> atualizar(@PathVariable Long pastaId, @Valid @RequestBody SalvarPastaDTO pastaDTO) {
		if (!pastaService.existsById(pastaId)) {
			return ResponseEntity.notFound().build();
		}
		
		Pasta pasta = PastaBuilder.toPasta(pastaDTO);
		pasta.setId(pastaId);
		return ResponseEntity.ok(PastaBuilder.toListarPastaDTO(pastaService.salvar(pasta)));
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

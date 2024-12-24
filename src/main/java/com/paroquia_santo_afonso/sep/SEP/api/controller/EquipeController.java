package com.paroquia_santo_afonso.sep.SEP.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.paroquia_santo_afonso.sep.SEP.domain.model.Pasta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.paroquia_santo_afonso.sep.SEP.api.dto.ListarEquipeDTO;
import com.paroquia_santo_afonso.sep.SEP.api.dto.SalvarEquipeDTO;
import com.paroquia_santo_afonso.sep.SEP.api.dto.builder.EquipeBuilder;
import com.paroquia_santo_afonso.sep.SEP.domain.model.Equipe;
import com.paroquia_santo_afonso.sep.SEP.domain.service.EquipeService;

import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/equipes")
public class EquipeController {
	
	private final EquipeService equipeService;
	
	public EquipeController(EquipeService equipeService) {
		this.equipeService = equipeService;
	}

	@GetMapping
	public List<ListarEquipeDTO> listar() {
		return equipeService.listar().stream()
				.map(EquipeBuilder::toListarEquipeDTO)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/{pastaId}")
	public ResponseEntity<ListarEquipeDTO> buscar(@PathVariable Long pastaId) {
		return equipeService.buscar(pastaId)
				.map(equipe -> ResponseEntity.ok(EquipeBuilder.toListarEquipeDTO(equipe)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ListarEquipeDTO adicionar(@Valid SalvarEquipeDTO salvarEquipeDTO,
									 @RequestParam(value = "arquivo", required = false) MultipartFile arquivo) {
		try {
			Equipe equipe = EquipeBuilder.toEquipe(salvarEquipeDTO);

			if (arquivo != null && !arquivo.isEmpty()) {
				Pasta pasta = new Pasta(arquivo.getOriginalFilename(), arquivo.getContentType(), arquivo.getBytes());
				equipe.setPasta(pasta);
			}

			return EquipeBuilder.toListarEquipeDTO(equipeService.salvar(equipe));
		}catch (IOException e) {
			throw new RuntimeException("Erro ao processar arquivo:" + e);
		}

	}
	
	@PutMapping("/{equipeId}/{pastaId}")
	public ResponseEntity<ListarEquipeDTO> atualizar(@PathVariable Long equipeId,
													 @PathVariable Long pastaId,
													 @Valid SalvarEquipeDTO salvarEquipeDTO,
													 @RequestParam(value = "arquivo", required = false) MultipartFile arquivo) {
		if (!equipeService.existsById(equipeId)) {
			return ResponseEntity.notFound().build();
		}

		try {
			Equipe equipe = EquipeBuilder.toEquipe(salvarEquipeDTO);
			equipe.setId(equipeId);

			if (arquivo != null && !arquivo.isEmpty()) {
				Pasta pasta = new Pasta(pastaId, arquivo.getOriginalFilename(), arquivo.getContentType(), arquivo.getBytes());
				equipe.setPasta(pasta);
			}

			return ResponseEntity.ok(EquipeBuilder.toListarEquipeDTO(equipeService.salvar(equipe)));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao processar arquivo: " + e);
		}

	} 

	@DeleteMapping("/{pastaId}")
	public ResponseEntity<Void> remover(@PathVariable Long pastaId) {
		if (!equipeService.existsById(pastaId)) {
			return ResponseEntity.notFound().build();
		}
		
		equipeService.excluir(pastaId);
		
		return ResponseEntity.noContent().build();
	}

}

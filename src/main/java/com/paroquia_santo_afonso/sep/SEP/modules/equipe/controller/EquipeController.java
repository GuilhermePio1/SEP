package com.paroquia_santo_afonso.sep.SEP.modules.equipe.controller;

import com.paroquia_santo_afonso.sep.SEP.common.base.dto.FileDownloadDTO;
import com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao.FiltroPaginacaoDto;
import com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao.FiltroPaginacaoResponse;
import com.paroquia_santo_afonso.sep.SEP.common.utils.ControllerUtils;
import com.paroquia_santo_afonso.sep.SEP.modules.equipe.dto.EquipeResponseDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.equipe.dto.EquipeRequestDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.equipe.service.EquipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/equipes")
@RequiredArgsConstructor
public class EquipeController {
	private final EquipeService equipeService;
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public EquipeResponseDTO criar(@Valid EquipeRequestDTO dto, @RequestParam(required = false, name = "arquivo") MultipartFile arquivo) {
		return equipeService.saveEntityWithFile(dto, arquivo);
	}

	@PostMapping("listar-paginado")
	public FiltroPaginacaoResponse<EquipeResponseDTO> listarPaginado(@RequestBody FiltroPaginacaoDto filtroPaginacao) {
		return equipeService.buscarPaginado(filtroPaginacao);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Page<EquipeResponseDTO> listarTodos(@PageableDefault() Pageable pageable) {
		return equipeService.findAllProjectedBy(pageable);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public EquipeResponseDTO buscarPorId(@PathVariable Long id) {
		return equipeService.findEntityWithFileById(id);
	}

	@GetMapping("/pasta/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Resource> downloadPasta(@PathVariable(name = "id") Long id) {
		FileDownloadDTO fileDownload = equipeService.downloadFile(id);
		return ControllerUtils.createFileResponse(fileDownload);
	}

	@PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public EquipeResponseDTO atualizar(@PathVariable Long id, @Valid EquipeRequestDTO dto, @RequestParam(required = false, name = "arquivo") MultipartFile arquivo) {
		return equipeService.updateEntityWithFile(dto, id, arquivo);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		equipeService.deletar(id);
	}

}

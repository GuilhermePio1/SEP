package com.paroquia_santo_afonso.sep.SEP.modules.encontro.controller;

import com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao.FiltroPaginacaoDto;
import com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao.FiltroPaginacaoResponse;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.dto.EncontroRequestDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.dto.EncontroResponseDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.projection.EncontroProjection;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.service.EncontroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/encontros")
@RequiredArgsConstructor
public class EncontroController {
	private final EncontroService encontroService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EncontroResponseDTO criar(@RequestBody @Valid EncontroRequestDTO dto) {
		return encontroService.criar(dto);
	}

	@PostMapping("listar-paginado")
	public FiltroPaginacaoResponse<EncontroResponseDTO> listarPaginado(@RequestBody FiltroPaginacaoDto filtroPaginacao) {
		return encontroService.buscarPaginado(filtroPaginacao);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Page<EncontroProjection> listar(@PageableDefault() Pageable pageable) {
		return encontroService.listar(pageable);
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public EncontroResponseDTO buscarPorId(@PathVariable Long id) {
		return encontroService.buscarPorId(id);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public EncontroResponseDTO atualizar(@PathVariable Long id, @RequestBody @Valid EncontroRequestDTO dto) {
		return encontroService.atualizar(id, dto);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		encontroService.deletar(id);
	}
}

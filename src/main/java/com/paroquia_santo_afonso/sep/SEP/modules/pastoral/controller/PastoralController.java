package com.paroquia_santo_afonso.sep.SEP.modules.pastoral.controller;

import com.paroquia_santo_afonso.sep.SEP.modules.pastoral.dto.PastoralRequestDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.pastoral.dto.PastoralResponseDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.pastoral.service.PastoralService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pastoral")
@RequiredArgsConstructor
public class PastoralController {
    private final PastoralService pastoralService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PastoralResponseDTO criar(@RequestBody PastoralRequestDTO pastoralRequestDTO) {
        return pastoralService.criar(pastoralRequestDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<PastoralResponseDTO> listar(@PageableDefault() Pageable pageable) {
        return pastoralService.listar(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PastoralResponseDTO buscar(@PathVariable Long id) {
        return pastoralService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PastoralResponseDTO editar(@PathVariable Long id, @RequestBody PastoralRequestDTO pastoralRequestDTO) {
        return pastoralService.atualizar(id, pastoralRequestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        pastoralService.deletar(id);
    }
}

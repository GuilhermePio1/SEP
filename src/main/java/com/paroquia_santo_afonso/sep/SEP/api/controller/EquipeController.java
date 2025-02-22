package com.paroquia_santo_afonso.sep.SEP.api.controller;

import com.paroquia_santo_afonso.sep.SEP.api.dto.ListarEquipeDTO;
import com.paroquia_santo_afonso.sep.SEP.api.dto.SalvarEquipeDTO;
import com.paroquia_santo_afonso.sep.SEP.api.dto.builder.EquipeBuilder;
import com.paroquia_santo_afonso.sep.SEP.domain.model.Equipe;
import com.paroquia_santo_afonso.sep.SEP.domain.model.Pasta;
import com.paroquia_santo_afonso.sep.SEP.domain.service.EquipeService;
import com.paroquia_santo_afonso.sep.SEP.domain.service.PastaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/equipes")
public class EquipeController {
    private final EquipeService equipeService;
    private final PastaService pastaService;

    public EquipeController(
            EquipeService equipeService,
            PastaService pastaService
    ) {
        this.pastaService = pastaService;
        this.equipeService = equipeService;
    }

    @GetMapping
    public List<ListarEquipeDTO> listar() {
        return equipeService.listar().stream()
                .map(EquipeBuilder::toListarEquipeDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{equipeId}")
    public ResponseEntity<ListarEquipeDTO> buscar(@PathVariable Long equipeId) {
        return equipeService.buscar(equipeId)
                .map(equipe -> ResponseEntity.ok(EquipeBuilder.toListarEquipeDTO(equipe)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pasta/{pastaId}")
    public ResponseEntity<byte[]> downloadPasta(@PathVariable("pastaId") Long pastaId) {
        try {
            Optional<Pasta> pastaOptional = pastaService.buscarPorId(pastaId);

            if (pastaOptional.isEmpty()) return ResponseEntity.notFound().build();

            Pasta pasta = pastaOptional.get();
            byte[] data = pasta.getArquivo();

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(pasta.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pasta.getNomeArquivo() + "\"")
                    .body(data);
        } catch (ResponseStatusException r) {
            log.info("Erro ao baixar arquivo", r);
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.info("Erro inesperado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ListarEquipeDTO adicionar(
            @Valid SalvarEquipeDTO salvarEquipeDTO,
            @RequestParam(value = "arquivo", required = false) MultipartFile arquivo
    ) {
        try {
            Equipe equipe = EquipeBuilder.toEquipe(salvarEquipeDTO);

            if (arquivo != null && !arquivo.isEmpty()) {
                Pasta pasta = pastaService.salvar(arquivo);
                equipe.setPasta(pasta);
            }

            return EquipeBuilder.toListarEquipeDTO(equipeService.salvar(equipe));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar arquivo:" + e);
        }

    }

    @PutMapping("/{equipeId}")
    @Transactional
    public ResponseEntity<ListarEquipeDTO> atualizar(
            @PathVariable Long equipeId,
            @Valid SalvarEquipeDTO salvarEquipeDTO,
            @RequestParam(value = "arquivo", required = false) MultipartFile arquivo
    ) {
        if (!equipeService.existsById(equipeId)) return ResponseEntity.notFound().build();

        try {
            Equipe equipe = EquipeBuilder.toEquipe(salvarEquipeDTO);
            equipe.setId(equipeId);

            Long pastaIdAtual = salvarEquipeDTO.getPastaId();
            if (arquivo != null && !arquivo.isEmpty()) {
                if (pastaIdAtual == null) {
                    Pasta pasta = pastaService.salvar(arquivo);
                    equipe.setPasta(pasta);
                } else {
                    Pasta pastaSaved = pastaService.atualizar(pastaIdAtual, arquivo);
                    equipe.setPasta(pastaSaved);
                }
            }

            return ResponseEntity.ok(EquipeBuilder.toListarEquipeDTO(equipeService.salvar(equipe)));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar arquivo: " + e);
        }

    }

    @Transactional
    @DeleteMapping("/{equipeId}")
    public ResponseEntity<Void> remover(@PathVariable Long equipeId) {
        if (!equipeService.existsById(equipeId)) return ResponseEntity.notFound().build();

        equipeService.excluir(equipeId);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @DeleteMapping("/{equipeId}/{pastaId}")
    public ResponseEntity<Void> removerArquivo(
            @PathVariable("equipeId") Long equipeId,
            @PathVariable("pastaId") Long pastaId
    ) {
        try {
            equipeService.excluirPasta(equipeId, pastaId);
            return ResponseEntity.noContent().build();
        } catch (ResponseStatusException r) {
            log.info("Erro ao excluir arquivo", r);
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.info("Erro inesperado", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

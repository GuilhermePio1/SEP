package com.paroquia_santo_afonso.sep.SEP.modules.equipista.controller;

import com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto.EquipistaDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.projection.EquipistaProjection;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.service.EquipistaService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/equipistas")
public class EquipistaController {
    private final EquipistaService equipistaService;

    public EquipistaController(EquipistaService equipistaService) {
        this.equipistaService = equipistaService;
    }

    @GetMapping
    public List<EquipistaProjection> listar(@PageableDefault() Pageable pageable) {
        return equipistaService.listarEquipistasProjections(pageable).stream().toList();
    }

    @GetMapping("{equipistaId}")
    public ResponseEntity<EquipistaDTO> buscar(@PathVariable Long equipistaId) {
        return ResponseEntity.ok(equipistaService.getEntityWithFile(equipistaId));
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> adicionar(@Valid EquipistaDTO equipistaDTO,
                                                  @RequestParam(value = "arquivo", required = false) MultipartFile arquivo) {
        EquipistaDTO equipistaSalvo = equipistaService.saveEntityWithFile(equipistaDTO, arquivo);

        equipistaService.salvarParticipacoesEncontros(equipistaDTO.getParticipacoesEncontrosDTO(), equipistaSalvo.getId());

        return ResponseEntity.ok("Equipista adicionado com sucesso.");
    }

    @PutMapping("/{equipistaId}")
    @Transactional
    public ResponseEntity<?> atualizar(
            @PathVariable Long equipistaId,
            @Valid EquipistaDTO equipistaDTO,
            @RequestParam(value = "arquivo", required = false) MultipartFile arquivo
    ) {
        try {
            equipistaService.atualizarEquipista(equipistaDTO, equipistaId,arquivo);

            return ResponseEntity.ok("Equipista atualizado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{equipistaId}")
    @Transactional
    public ResponseEntity<Void> remover(@PathVariable Long equipistaId) {
        equipistaService.deletarEquipista(equipistaId);
        return ResponseEntity.noContent().build();
    }
}

package com.paroquia_santo_afonso.sep.SEP.api.controller;

import com.paroquia_santo_afonso.sep.SEP.api.dto.PastoralDTO;
import com.paroquia_santo_afonso.sep.SEP.api.dto.builder.PastoralBuilder;
import com.paroquia_santo_afonso.sep.SEP.domain.model.Pastoral;
import com.paroquia_santo_afonso.sep.SEP.domain.service.PastoralService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pastoral")
public class PastoralController {
    private final PastoralService pastoralService;

    public PastoralController(PastoralService pastoralService) {
        this.pastoralService = pastoralService;
    }

    @GetMapping
    public List<PastoralDTO> listar() {
        return pastoralService.listar().stream()
                .map(PastoralBuilder::toPastoralDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{pastoralId}")
    public ResponseEntity<PastoralDTO> buscar(@PathVariable Long pastoralId) {
        return pastoralService.buscar(pastoralId)
                .map(PastoralBuilder::toPastoralDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PastoralDTO adicionar(@Valid @RequestBody PastoralDTO pastoralDTO) {
        Pastoral pastoral = PastoralBuilder.toPastoral(pastoralDTO);
        return PastoralBuilder.toPastoralDTO(pastoralService.salvar(pastoral));
    }

    @PutMapping("/{pastoralId}")
    public ResponseEntity<PastoralDTO> atualizar(@PathVariable Long pastoralId, @Valid @RequestBody PastoralDTO pastoralDTO) {
        if (!pastoralService.existsById(pastoralId)) {
            return ResponseEntity.notFound().build();
        }
        Pastoral pastoral = PastoralBuilder.toPastoral(pastoralDTO);
        pastoral.setId(pastoralId);

        return ResponseEntity.ok(PastoralBuilder.toPastoralDTO(pastoralService.salvar(pastoral)));
    }

    @DeleteMapping("/{pastoralId}")
    public ResponseEntity<Void> remover(@PathVariable Long pastoralId) {
        if (!pastoralService.existsById(pastoralId)) {
            return ResponseEntity.notFound().build();
        }

        pastoralService.excluir(pastoralId);

        return ResponseEntity.noContent().build();
    }
}

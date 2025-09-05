package com.paroquia_santo_afonso.sep.SEP.modules.equipista.controller;

import com.paroquia_santo_afonso.sep.SEP.common.base.dto.FileDownloadDTO;
import com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao.FiltroPaginacaoDto;
import com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao.FiltroPaginacaoResponse;
import com.paroquia_santo_afonso.sep.SEP.common.utils.ControllerUtils;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto.EquipistaRequestDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto.EquipistaResponseDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.projection.EquipistaProjection;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.service.EquipistaService;
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
@RequestMapping("/equipista")
public class EquipistaController {
    private final EquipistaService equipistaService;

    public EquipistaController(EquipistaService equipistaService) {
        this.equipistaService = equipistaService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public EquipistaResponseDTO create(EquipistaRequestDTO equipistaRequestDTO, @RequestParam(required = false, name = "arquivo") MultipartFile arquivo) {
        return equipistaService.save(equipistaRequestDTO, arquivo);
    }

    @PostMapping("listar-paginado")
    public FiltroPaginacaoResponse<EquipistaResponseDTO> listarPaginado(@RequestBody FiltroPaginacaoDto filtroPaginacao) {
        return equipistaService.buscarPaginado(filtroPaginacao);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<EquipistaProjection> listarTodos(@PageableDefault() Pageable pageable) {
        return equipistaService.findAllProjectedBy(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EquipistaResponseDTO buscarPorId(@PathVariable Long id) {
        return equipistaService.findEntityWithFileById(id);
    }

    @GetMapping("/foto/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Resource> downloadFoto(@PathVariable Long id) {
        FileDownloadDTO fileDownloadDTO = equipistaService.downloadFile(id);
        return ControllerUtils.createFileResponse(fileDownloadDTO);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public EquipistaResponseDTO atualizar(@PathVariable Long id, EquipistaRequestDTO equipistaRequestDTO, @RequestParam("arquivo") MultipartFile arquivo) {
        return equipistaService.update(equipistaRequestDTO, id, arquivo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        equipistaService.delete(id);
    }
}

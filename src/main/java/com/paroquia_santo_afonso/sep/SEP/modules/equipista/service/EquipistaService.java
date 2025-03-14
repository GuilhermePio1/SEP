package com.paroquia_santo_afonso.sep.SEP.modules.equipista.service;

import com.paroquia_santo_afonso.sep.SEP.common.base.repository.FileRepository;
import com.paroquia_santo_afonso.sep.SEP.common.base.service.impl.FileServiceImpl;
import com.paroquia_santo_afonso.sep.SEP.common.exception.EntidadeNaoEncontradaException;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.builder.EquipistaBuilder;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.builder.ParticipacaoEncontroBuilder;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto.EquipistaDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto.ParticipacaoEncontroDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.projection.EquipistaProjection;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.Equipista;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.repository.EquipistaRepository;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.repository.ParticipacaoEncontroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EquipistaService extends FileServiceImpl<EquipistaDTO, Equipista, EquipistaBuilder> {

    private final EquipistaRepository equipistaRepository;
    private final ParticipacaoEncontroRepository participacaoEncontroRepository;

    public EquipistaService(FileRepository<Equipista, Long> fileRepository, EquipistaBuilder fileBuilder, EquipistaRepository equipistaRepository, ParticipacaoEncontroRepository participacaoEncontroRepository) {
        super(fileRepository, fileBuilder);
        this.equipistaRepository = equipistaRepository;
        this.participacaoEncontroRepository = participacaoEncontroRepository;
    }

    public Page<EquipistaProjection> listarEquipistasProjections(Pageable pageable) {
        return equipistaRepository.findAllProjectedBy(pageable);
    }

    public void salvarParticipacoesEncontros(List<ParticipacaoEncontroDTO> participacoesEncontrosDto, Long equipistaId) {
        if (participacoesEncontrosDto != null && !participacoesEncontrosDto.isEmpty() && equipistaId != null) {
            participacoesEncontrosDto.forEach(participacaoEncontroDTO -> participacaoEncontroDTO.setEquipistaId(equipistaId));
            participacaoEncontroRepository.saveAll(
                    participacoesEncontrosDto.stream()
                            .map(ParticipacaoEncontroBuilder::toParticipacaoEncontro)
                            .collect(Collectors.toSet())
            );
        }
    }

    public void atualizarEquipista(EquipistaDTO equipistaDTO, Long equipistaId, MultipartFile arquivo) throws IOException {
        if (equipistaRepository.existsById(equipistaId)) {
            equipistaDTO.setId(equipistaId);
            equipistaDTO.setFileData(arquivo.getBytes());
            equipistaDTO.setFileName(arquivo.getOriginalFilename());
            equipistaDTO.setFileType(arquivo.getContentType());

            equipistaRepository.save(new EquipistaBuilder().toEntity(equipistaDTO));
            salvarParticipacoesEncontros(equipistaDTO.getParticipacoesEncontrosDTO(), equipistaId);
        } else {
            throw new EntidadeNaoEncontradaException("Equipista não encontrado");
        }
    }
    
    public void deletarEquipista(Long equipistaId) {
        if (equipistaRepository.existsById(equipistaId)) {
            equipistaRepository.deleteById(equipistaId);
        }
    }
}

package com.paroquia_santo_afonso.sep.SEP.modules.equipista.service;

import com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao.FiltroPaginacaoDto;
import com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao.FiltroPaginacaoResponse;
import com.paroquia_santo_afonso.sep.SEP.common.base.service.impl.FileServiceImpl;
import com.paroquia_santo_afonso.sep.SEP.common.exception.EquipistaNotFoundException;
import com.paroquia_santo_afonso.sep.SEP.common.exception.ResourceNotFoundException;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto.*;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.mapper.EquipistaMapper;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.mapper.ParticipacaoEncontroMapper;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.Equipista;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.ParticipacaoEncontro;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.projection.EquipistaProjection;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.repository.EquipistaRepository;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.repository.ParticipacaoEncontroRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class EquipistaService extends FileServiceImpl<Equipista, EquipistaMapper, EquipistaRepository, EquipistaResponseDTO, EquipistaRequestDTO> {
    private final ParticipacaoEncontroRepository participacaoEncontroRepository;
    private final ParticipacaoEncontroMapper participacaoEncontroMapper;

    public EquipistaService(EquipistaMapper mapper, EquipistaRepository repository, ParticipacaoEncontroRepository participacaoEncontroRepository, ParticipacaoEncontroMapper participacaoEncontroMapper) {
        super(mapper, repository);
        this.participacaoEncontroRepository = participacaoEncontroRepository;
        this.participacaoEncontroMapper = participacaoEncontroMapper;
    }

    public FiltroPaginacaoResponse<EquipistaResponseDTO> buscarPaginado(FiltroPaginacaoDto filtroPaginacaoResponse) {
        return this.repository.buscarPaginado(filtroPaginacaoResponse);
    }

    @Override
    protected ResourceNotFoundException createNotFoundException(Long id) {
        return new EquipistaNotFoundException(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public EquipistaResponseDTO save(EquipistaRequestDTO equipistaRequestDTO, MultipartFile file) {
        EquipistaResponseDTO equipistaResponseDTO = super.saveEntityWithFile(equipistaRequestDTO, file);
        List<ParticipacaoEncontroResponseDTO> participacoesEncontros = saveParticipacaoEncontro(equipistaRequestDTO.getParticipacoesEncontro(), equipistaResponseDTO.getId());
        equipistaResponseDTO.setParticipacoesEncontro(participacoesEncontros);
        return equipistaResponseDTO;
    }

    private List<ParticipacaoEncontroResponseDTO> saveParticipacaoEncontro(List<ParticipacaoEncontroRequestDTO> participacoesEncontrosDTO, Long idEquipista) {
        if (participacoesEncontrosDTO != null && !participacoesEncontrosDTO.isEmpty()) {
            List<ParticipacaoEncontro> participacaoEncontros = participacoesEncontrosDTO.stream()
                    .map(participacaoEncontroMapper::toEntity)
                    .peek(participacaoEncontro -> participacaoEncontro.setEquipista(new Equipista(idEquipista)))
                    .toList();

            return participacaoEncontroRepository.saveAll(participacaoEncontros).stream()
                    .map(participacaoEncontroMapper::toResponseDTO)
                    .toList();
        }

        return Collections.emptyList();
    }

    @Transactional(readOnly = true)
    public Page<EquipistaProjection> findAllProjectedBy(Pageable pageable) {
        return repository.findAllProjectedBy(pageable);
    }

    @Transactional(rollbackFor = Exception.class)
    public EquipistaResponseDTO update(EquipistaRequestDTO equipistaRequestDTO, Long idEquipista, MultipartFile file) {
        EquipistaResponseDTO equipistaResponseDTO = super.updateEntityWithFile(equipistaRequestDTO, idEquipista, file);
        List<ParticipacaoEncontroResponseDTO> participacaoEncontroResponseDTOS = updateParticipacoesEncontros(equipistaRequestDTO.getParticipacoesEncontro(), idEquipista);
        equipistaResponseDTO.setParticipacoesEncontro(participacaoEncontroResponseDTOS);
        return equipistaResponseDTO;
    }

    private List<ParticipacaoEncontroResponseDTO> updateParticipacoesEncontros(List<ParticipacaoEncontroRequestDTO> participacoesEncontrosDTO, Long idEquipista) {
        List<ParticipacaoEncontroResponseDTO> participacaoEncontroResponseDTOS;

        List<ParticipacaoEncontro> cadastrarParticipacoes = getParticipacoesEncontrosByAcao(participacoesEncontrosDTO, AcaoParticipacaoEncontro.ATUALIZAR);
        List<ParticipacaoEncontro> deletarParticipacoes = getParticipacoesEncontrosByAcao(participacoesEncontrosDTO, AcaoParticipacaoEncontro.DELETAR);
        List<ParticipacaoEncontroRequestDTO> atualizarParticipacoes = participacoesEncontrosDTO != null
                ? participacoesEncontrosDTO.stream()
                .filter(participacaoEncontroRequestDTO -> Objects.nonNull(participacaoEncontroRequestDTO.getAcaoParticipacaoEncontroEnum()) && participacaoEncontroRequestDTO.getAcaoParticipacaoEncontroEnum().equals(AcaoParticipacaoEncontro.ATUALIZAR)).toList()
                : Collections.emptyList();

        participacaoEncontroResponseDTOS = participacaoEncontroRepository.saveAll(cadastrarParticipacoes)
                .stream().map(participacaoEncontroMapper::toResponseDTO)
                .toList();

        participacaoEncontroRepository.deleteAll(deletarParticipacoes);

        if (!atualizarParticipacoes.isEmpty()) {
            atualizarParticipacoes.forEach(atualizarParticipacao -> {
                ParticipacaoEncontro participacaoEncontro = participacaoEncontroRepository.findFirstByEquipistaIdAndEquipeIdOrderByDataAtualizacao(idEquipista, atualizarParticipacao.getIdEquipe()).orElseThrow(() -> createNotFoundException(idEquipista));
                participacaoEncontroMapper.updateEntityFromDTO(participacaoEncontro, atualizarParticipacao);
                participacaoEncontroResponseDTOS.add(participacaoEncontroMapper.toResponseDTO(participacaoEncontroRepository.save(participacaoEncontro)));
            });
        }

        return participacaoEncontroResponseDTOS;
    }

    private List<ParticipacaoEncontro> getParticipacoesEncontrosByAcao(List<ParticipacaoEncontroRequestDTO> participacoesEncontrosDTO, AcaoParticipacaoEncontro acaoParticipacaoEncontro) {
        return participacoesEncontrosDTO != null ? participacoesEncontrosDTO.stream()
                .filter(participacaoEncontroDTO -> Objects.nonNull(participacaoEncontroDTO.getAcaoParticipacaoEncontroEnum()) && participacaoEncontroDTO.getAcaoParticipacaoEncontroEnum().equals(acaoParticipacaoEncontro))
                .map(participacaoEncontroMapper::toEntity)
                .toList() : Collections.emptyList();
    }

    @Transactional
    public void delete(Long id) {
        Equipista equipista = repository.findById(id).orElseThrow(() -> createNotFoundException(id));
        repository.delete(equipista);
    }

}
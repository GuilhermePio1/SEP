package com.paroquia_santo_afonso.sep.SEP.modules.pastoral.service;

import com.paroquia_santo_afonso.sep.SEP.common.exception.PastoralUsadaException;
import com.paroquia_santo_afonso.sep.SEP.common.exception.ResourceNotFoundException;
import com.paroquia_santo_afonso.sep.SEP.modules.pastoral.dto.PastoralRequestDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.pastoral.dto.PastoralResponseDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.pastoral.mapper.PastoralMapper;
import com.paroquia_santo_afonso.sep.SEP.modules.pastoral.model.Pastoral;
import com.paroquia_santo_afonso.sep.SEP.modules.pastoral.repository.PastoralRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PastoralService {
    private final PastoralRepository pastoralRepository;
    private final PastoralMapper pastoralMapper;

    @Transactional
    public PastoralResponseDTO criar(PastoralRequestDTO dto) {
        Pastoral pastoral = pastoralMapper.toEntity(dto);
        Pastoral saved = pastoralRepository.save(pastoral);
        return pastoralMapper.toResponseDTO(saved);
    }

    @Transactional(readOnly = true)
    public Page<PastoralResponseDTO> listar(Pageable pageable) {
        return Optional.of(pastoralRepository.findAllProjectBy(pageable)
                .map(pastoralMapper::toResponseDTO))
                .orElse(Page.empty());
    }

    @Transactional(readOnly = true)
    public PastoralResponseDTO buscarPorId(Long id) {
        return pastoralRepository.findById(id).map(pastoralMapper::toResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Pastoral não encontrada."));
    }

    @Transactional
    public PastoralResponseDTO atualizar(Long id, PastoralRequestDTO dto) {
        Pastoral pastoral = pastoralRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pastoral não encontrada."));

        pastoralMapper.updateEntityFromDTO(dto, pastoral);
        Pastoral updated = pastoralRepository.save(pastoral);
        return pastoralMapper.toResponseDTO(updated);
    }

    @Transactional
    public void deletar(Long id) {
        if (!pastoralRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pastoral não encontrada.");
        }

        try {
            pastoralRepository.deleteById(id);
            pastoralRepository.flush();
        }catch (DataIntegrityViolationException e) {
            throw new PastoralUsadaException("Está pastoral ainda está sendo usada no sistema, não é possível excluí-la.");
        }

    }
}

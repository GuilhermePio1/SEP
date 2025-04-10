package com.paroquia_santo_afonso.sep.SEP.modules.encontro.service;

import com.paroquia_santo_afonso.sep.SEP.common.exception.EncontroNotFoundException;
import com.paroquia_santo_afonso.sep.SEP.common.exception.EncontroUsadoException;
import com.paroquia_santo_afonso.sep.SEP.common.exception.ResourceNotFoundException;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.dto.EncontroRequestDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.dto.EncontroResponseDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.mapper.EncontroMapper;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.model.Encontro;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.projection.EncontroProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.paroquia_santo_afonso.sep.SEP.modules.encontro.repository.EncontroRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EncontroService {
	
	private final EncontroRepository encontroRepository;
	private final EncontroMapper encontroMapper;

	@Transactional
	public EncontroResponseDTO criar(EncontroRequestDTO dto) {
		Encontro encontro = encontroMapper.toEntity(dto);
		Encontro salvo = encontroRepository.save(encontro);
		return encontroMapper.toResponseDTO(salvo);
	}

	@Transactional(readOnly = true)
	public Page<EncontroProjection> listar(Pageable pageable) {
		return encontroRepository.findAllProjectedBy(pageable);
	}

	@Transactional(readOnly = true)
	public EncontroResponseDTO buscarPorId(Long id) {
		return encontroRepository.findById(id)
				.map(encontroMapper::toResponseDTO)
				.orElseThrow(() -> new EncontroNotFoundException(id));
	}

	@Transactional
	public EncontroResponseDTO atualizar(Long id, EncontroRequestDTO dto) {
		Encontro encontro = encontroRepository.findById(id)
				.orElseThrow(() -> new EncontroNotFoundException(id));

		encontroMapper.updateEntityFromDTO(encontro, dto);
		Encontro atualizado = encontroRepository.save(encontro);
		return encontroMapper.toResponseDTO(atualizado);
	}

	@Transactional
	public void deletar(Long id) {
		if (!encontroRepository.existsById(id)) {
			throw new ResourceNotFoundException("Encontro não encontrado.");
		}

		try {
			encontroRepository.deleteById(id);
			encontroRepository.flush();
		}catch (DataIntegrityViolationException e) {
			throw new EncontroUsadoException("Este encontro ainda está sendo usado no sistema, não é possível excluí-lo.");
		}

	}
}

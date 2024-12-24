package com.paroquia_santo_afonso.sep.SEP.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.paroquia_santo_afonso.sep.SEP.api.dto.ListarEncontroDTO;
import com.paroquia_santo_afonso.sep.SEP.api.dto.builder.EncontroBuilder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.paroquia_santo_afonso.sep.SEP.domain.exception.EncontroNaoEncontradoException;
import com.paroquia_santo_afonso.sep.SEP.domain.model.Encontro;
import com.paroquia_santo_afonso.sep.SEP.domain.repository.EncontroRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EncontroService {
	
	private final EncontroRepository encontroRepository;
	
	public EncontroService(EncontroRepository encontroRepository) {
		this.encontroRepository = encontroRepository;
	}

	@Transactional(readOnly = true)
	public List<ListarEncontroDTO> listar() {
		return encontroRepository.findAll().stream()
				.map(EncontroBuilder::toListarEncontroDTO)
				.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public Optional<ListarEncontroDTO> buscar(Long encontroId) {
		return encontroRepository.findById(encontroId).map(EncontroBuilder::toListarEncontroDTO);
	}
	
	public Boolean existsById(Long encontroId) {
		return encontroRepository.existsById(encontroId);
	}
	
	public Encontro salvar(Encontro encontro) {
		return encontroRepository.save(encontro);
	}
	
	public void excluir(Long encontroId) {
		try {
			encontroRepository.deleteById(encontroId);
		} catch (EmptyResultDataAccessException e) {
			throw new EncontroNaoEncontradoException(encontroId);
		}
	}

}

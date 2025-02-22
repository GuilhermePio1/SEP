package com.paroquia_santo_afonso.sep.SEP.modules.encontro.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.paroquia_santo_afonso.sep.SEP.modules.encontro.dto.ListarEncontroDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.builder.EncontroBuilder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.paroquia_santo_afonso.sep.SEP.common.exception.EncontroNaoEncontradoException;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.model.Encontro;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.repository.EncontroRepository;
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

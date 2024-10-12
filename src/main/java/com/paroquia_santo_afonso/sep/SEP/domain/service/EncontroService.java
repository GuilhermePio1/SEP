package com.paroquia_santo_afonso.sep.SEP.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.paroquia_santo_afonso.sep.SEP.domain.exception.EncontroNaoEncontradoException;
import com.paroquia_santo_afonso.sep.SEP.domain.model.Encontro;
import com.paroquia_santo_afonso.sep.SEP.domain.repository.EncontroRepository;

@Service
public class EncontroService {
	
	private final EncontroRepository encontroRepository;
	
	public EncontroService(EncontroRepository encontroRepository) {
		this.encontroRepository = encontroRepository;
	}

	public List<Encontro> listar() {
		return encontroRepository.findAll();
	}
	
	public Optional<Encontro> buscar(Long encontroId) {
		return encontroRepository.findById(encontroId);
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

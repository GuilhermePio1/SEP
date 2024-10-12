package com.paroquia_santo_afonso.sep.SEP.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.paroquia_santo_afonso.sep.SEP.domain.exception.NegocioException;
import com.paroquia_santo_afonso.sep.SEP.domain.model.Pasta;
import com.paroquia_santo_afonso.sep.SEP.domain.repository.EncontroRepository;
import com.paroquia_santo_afonso.sep.SEP.domain.repository.PastaRepository;

@Service
public class PastaService {
	
	private final PastaRepository pastaRepository;
	private final EncontroRepository encontroRepository;
	
	public PastaService(PastaRepository pastaRepository, EncontroRepository encontroRepository) {
		this.pastaRepository = pastaRepository;
		this.encontroRepository = encontroRepository;
	}	
	
	public List<Pasta> listar() {
		return pastaRepository.findAll();
	}
	
	public Optional<Pasta> buscar(Long pastaId) {
		return pastaRepository.findById(pastaId);
	}
	
	public Boolean existsById(Long pastaId) {
		return pastaRepository.existsById(pastaId);
	}
	
	public Pasta salvar(Pasta pasta) {
		Long encontroId = pasta.getEncontro().getId();
		
		encontroRepository.findById(encontroId)
				.orElseThrow(() -> new NegocioException("Encontro não encontrado"));
		
		return pastaRepository.save(pasta);
	}
	
	public void excluir(Long pastaId) {
		try {
			pastaRepository.deleteById(pastaId);
		} catch (EmptyResultDataAccessException e) {
			throw new NegocioException("Pasta não encontrada");
		} 
	}
}

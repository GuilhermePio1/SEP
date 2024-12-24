package com.paroquia_santo_afonso.sep.SEP.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.paroquia_santo_afonso.sep.SEP.domain.exception.NegocioException;
import com.paroquia_santo_afonso.sep.SEP.domain.model.Equipe;
import com.paroquia_santo_afonso.sep.SEP.domain.repository.EncontroRepository;
import com.paroquia_santo_afonso.sep.SEP.domain.repository.EquipeRepository;

@Service
public class EquipeService {
	
	private final EquipeRepository equipeRepository;
	private final EncontroRepository encontroRepository;
	
	public EquipeService(EquipeRepository equipeRepository, EncontroRepository encontroRepository) {
		this.equipeRepository = equipeRepository;
		this.encontroRepository = encontroRepository;
	}	
	
	public List<Equipe> listar() {
		return equipeRepository.findAll();
	}
	
	public Optional<Equipe> buscar(Long equipeId) {
		return equipeRepository.findById(equipeId);
	}
	
	public Boolean existsById(Long equipeId) {
		return equipeRepository.existsById(equipeId);
	}
	
	public Equipe salvar(Equipe equipe) {
		Long encontroId = equipe.getEncontro().getId();
		
		encontroRepository.findById(encontroId)
				.orElseThrow(() -> new NegocioException("Encontro não encontrado"));
		
		return equipeRepository.save(equipe);
	}
	
	public void excluir(Long equipeId) {
		try {
			equipeRepository.deleteById(equipeId);
		} catch (EmptyResultDataAccessException e) {
			throw new NegocioException("Pasta não encontrada");
		} 
	}
}

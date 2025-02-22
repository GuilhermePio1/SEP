package com.paroquia_santo_afonso.sep.SEP.modules.equipe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.paroquia_santo_afonso.sep.SEP.common.exception.NegocioException;
import com.paroquia_santo_afonso.sep.SEP.modules.equipe.model.Equipe;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.repository.EncontroRepository;
import com.paroquia_santo_afonso.sep.SEP.modules.equipe.repository.EquipeRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EquipeService {

	private final EquipeRepository equipeRepository;
	private final EncontroRepository encontroRepository;
	private final PastaService pastaService;

	public EquipeService(
			EquipeRepository equipeRepository,
			EncontroRepository encontroRepository,
			PastaService pastaService
	) {
		this.equipeRepository = equipeRepository;
		this.encontroRepository = encontroRepository;
		this.pastaService = pastaService;
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

	@Transactional
	public void excluirPasta(Long equipeId, Long pastaId) {
		Optional<Equipe> equipe = equipeRepository.findById(equipeId);
		if (equipe.isEmpty()) throw new NegocioException("Equipe não encontrada");

		try {
			pastaService.excluir(pastaId);
		} catch (EmptyResultDataAccessException e) {
			throw new NegocioException("Pasta não encontrada");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao excluir pasta", e);
		}

		Equipe equipeAtual = equipe.get();
		equipeAtual.setPasta(null);

		try {
			equipeRepository.save(equipeAtual);
		} catch (EmptyResultDataAccessException e) {
			throw new NegocioException("Equipe não encontrada");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar equipe", e);
		}
	}
}

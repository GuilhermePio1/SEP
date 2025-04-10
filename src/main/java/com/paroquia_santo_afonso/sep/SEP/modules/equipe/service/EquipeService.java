package com.paroquia_santo_afonso.sep.SEP.modules.equipe.service;

import com.paroquia_santo_afonso.sep.SEP.common.base.service.impl.FileServiceImpl;
import com.paroquia_santo_afonso.sep.SEP.common.exception.EquipeNotFoundException;
import com.paroquia_santo_afonso.sep.SEP.common.exception.EquipeUsadaException;
import com.paroquia_santo_afonso.sep.SEP.common.exception.ResourceNotFoundException;
import com.paroquia_santo_afonso.sep.SEP.modules.equipe.dto.EquipeResponseDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.equipe.dto.EquipeRequestDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.equipe.mapper.EquipeMapper;
import com.paroquia_santo_afonso.sep.SEP.modules.equipe.model.Equipe;
import com.paroquia_santo_afonso.sep.SEP.modules.equipe.repository.EquipeRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EquipeService extends FileServiceImpl<Equipe, EquipeMapper, EquipeRepository, EquipeResponseDTO, EquipeRequestDTO> {

	public EquipeService(EquipeMapper mapper, EquipeRepository repository) {
		super(mapper, repository);
	}

	@Override
	protected ResourceNotFoundException createNotFoundException(Long id) {
		return new EquipeNotFoundException(id);
	}

	@Transactional(readOnly = true)
	public Page<EquipeResponseDTO> findAllProjectedBy(Pageable pageable) {
		return Optional.of(repository.findAllProjectedBy(pageable).map(mapper::toResponseDTO)).orElse(Page.empty(pageable));
	}

	@Transactional
	public void deletar(Long id) {
		if (!repository.existsById(id)) {
			throw createNotFoundException(id);
		}

		try {
			repository.deleteById(id);
			repository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EquipeUsadaException("Esta equipe ainda está sendo usada no sistema, não é possível excluí-la.");
		}

	}

}

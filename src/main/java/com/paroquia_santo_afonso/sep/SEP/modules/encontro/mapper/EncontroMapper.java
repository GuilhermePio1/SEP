package com.paroquia_santo_afonso.sep.SEP.modules.encontro.mapper;

import com.paroquia_santo_afonso.sep.SEP.modules.encontro.dto.EncontroRequestDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.dto.EncontroResponseDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.dto.EquipeDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.model.Encontro;
import com.paroquia_santo_afonso.sep.SEP.modules.equipe.model.Equipe;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class EncontroMapper {

	public Encontro toEntity(EncontroRequestDTO dto) {
		return Encontro.builder()
				.nome(dto.getNome())
				.build();
	}

	public EncontroResponseDTO toResponseDTO(Encontro encontro) {
		return EncontroResponseDTO.builder()
				.id(encontro.getId())
				.nome(encontro.getNome())
				.equipes(Optional.ofNullable(encontro.getEquipes())
						.map(equipes -> equipes.stream()
								.map(this::toEquipeDTO)
								.toList())
				.orElse(Collections.emptyList()))
				.build();
	}

	public void updateEntityFromDTO(Encontro encontro, EncontroRequestDTO dto) {
		encontro.setNome(dto.getNome());
	}

	private EquipeDTO toEquipeDTO(Equipe equipe) {
		return EquipeDTO.builder()
				.id(equipe.getId())
				.nome(equipe.getNome())
				.build();
	}
}

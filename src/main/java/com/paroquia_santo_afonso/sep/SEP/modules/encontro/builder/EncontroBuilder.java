package com.paroquia_santo_afonso.sep.SEP.modules.encontro.builder;

import java.util.stream.Collectors;

import com.paroquia_santo_afonso.sep.SEP.modules.equipe.builder.EquipeBuilder;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.dto.ListarEncontroDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.dto.SalvarEncontroDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.model.Encontro;

public class EncontroBuilder {
	public static ListarEncontroDTO toListarEncontroDTO(Encontro encontro) {
		return ListarEncontroDTO.builder()
				.id(encontro.getId())
				.nome(encontro.getNome())
				.equipes(encontro.getEquipes() != null ? encontro.getEquipes().stream()
						.map(EquipeBuilder::toListarEquipeDTOParaEncontro)
						.collect(Collectors.toList()) : null)
				.build();
	}

	public static Encontro toEncontro(SalvarEncontroDTO salvarEncontroDTO) {
		return Encontro.builder()
				.nome(salvarEncontroDTO.getNome())
				.build();
	}
}

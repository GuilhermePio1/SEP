package com.paroquia_santo_afonso.sep.SEP.modules.encontro.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EncontroResponseDTO {
	private Long id;
	private String nome;
	private List<EquipeDTO> equipes;
}

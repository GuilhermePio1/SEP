package com.paroquia_santo_afonso.sep.SEP.modules.encontro.dto;

import java.util.List;

import com.paroquia_santo_afonso.sep.SEP.modules.equipe.dto.ListarEquipeDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ListarEncontroDTO {
	private Long id;
	
	private String nome;
	
	private List<ListarEquipeDTO> equipes;
}

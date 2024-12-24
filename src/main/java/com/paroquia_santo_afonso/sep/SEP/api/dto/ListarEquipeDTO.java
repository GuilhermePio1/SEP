package com.paroquia_santo_afonso.sep.SEP.api.dto;

import com.paroquia_santo_afonso.sep.SEP.domain.model.Pasta;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ListarEquipeDTO {
	private Long id;
	
	private String equipe;
	
	private Pasta pasta;
	
	private ListarEncontroEquipeDTO encontro;
}

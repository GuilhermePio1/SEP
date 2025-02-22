package com.paroquia_santo_afonso.sep.SEP.modules.equipe.dto;

import com.paroquia_santo_afonso.sep.SEP.modules.equipe.model.Pasta;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.dto.ListarEncontroEquipeDTO;
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

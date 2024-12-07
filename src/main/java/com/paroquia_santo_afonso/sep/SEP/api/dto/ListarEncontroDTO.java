package com.paroquia_santo_afonso.sep.SEP.api.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ListarEncontroDTO {
	private Long id;
	
	private String nome;
	
	private List<ListarPastaDTO> pastas;
}

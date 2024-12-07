package com.paroquia_santo_afonso.sep.SEP.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ListarPastaDTO {
	private Long id;
	
	private String equipe;
	
	private String arquivoBase64;
	
	private Long encontroId;
}

package com.paroquia_santo_afonso.sep.SEP.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SalvarPastaDTO {
	@NotBlank(message = "A equipe é obrigatória")
	private String equipe;
	
	private String arquivoBase64;
	
	private String encontroId;
}

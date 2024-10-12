package com.paroquia_santo_afonso.sep.SEP.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PastaDTO {
	private Long id;
	
	@NotBlank(message = "A equipe é obrigatória")
	private String equipe;
	
	private String arquivoBase64;
}

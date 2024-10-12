package com.paroquia_santo_afonso.sep.SEP.api.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EncontroDTO {
	private Long id;
	
	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	
	private List<PastaDTO> pastas;
}

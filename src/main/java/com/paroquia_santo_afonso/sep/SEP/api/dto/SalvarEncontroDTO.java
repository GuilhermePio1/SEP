package com.paroquia_santo_afonso.sep.SEP.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SalvarEncontroDTO {

	@NotBlank(message = "O nome é obrigatório")
	private String nome;

	private String descricao;
}

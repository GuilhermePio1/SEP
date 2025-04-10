package com.paroquia_santo_afonso.sep.SEP.modules.encontro.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EncontroRequestDTO {
	@NotBlank(message = "O nome é obrigatório")
	private String nome;

}

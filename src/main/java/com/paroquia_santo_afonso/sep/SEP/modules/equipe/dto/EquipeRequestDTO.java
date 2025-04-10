package com.paroquia_santo_afonso.sep.SEP.modules.equipe.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipeRequestDTO {
	@NotBlank(message = "O nome da equipe é obrigatório.")
	private String nome;

	@NotNull
	@Positive(message = "É preciso informar de qual encontro é essa equipe.")
	private Long encontroId;
}

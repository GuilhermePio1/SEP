package com.paroquia_santo_afonso.sep.SEP.modules.equipe.dto;

import com.paroquia_santo_afonso.sep.SEP.common.base.dto.FileBaseDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EquipeResponseDTO extends FileBaseDTO {
	private Long id;
	private String nome;
	private EncontroDTO encontro;

	/**
	 * Construtor para o SQLResultSetMapping "EquipeResponseDTOMapping"
	 * @param id identificador da equipe
	 * @param nome nome da equipe
	 */
	public EquipeResponseDTO(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
}


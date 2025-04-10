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
}


package com.paroquia_santo_afonso.sep.SEP.modules.equipe.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EncontroDTO {
    private Long id;
    private String nome;
}

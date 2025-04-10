package com.paroquia_santo_afonso.sep.SEP.modules.encontro.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EquipeDTO {
    private Long id;
    private String nome;
}

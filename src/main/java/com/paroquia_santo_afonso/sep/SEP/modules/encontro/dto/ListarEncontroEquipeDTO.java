package com.paroquia_santo_afonso.sep.SEP.modules.encontro.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ListarEncontroEquipeDTO {
    private Long id;
    private String nome;
}

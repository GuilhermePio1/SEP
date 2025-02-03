package com.paroquia_santo_afonso.sep.SEP.domain.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Embeddable
public class AreaAtuacao {
    @Enumerated(EnumType.STRING)
    private Formacao formacao;

    @Enumerated(EnumType.STRING)
    private Ocupacao ocupacao;

    @Enumerated(EnumType.STRING)
    private Profissao profissao;

    private String habilidades;
}

package com.paroquia_santo_afonso.sep.SEP.modules.equipista.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class AreaAtuacao {
    @Enumerated(EnumType.STRING)
    private Formacao formacao;

    @Enumerated(EnumType.STRING)
    private Ocupacao ocupacao;

    @Enumerated(EnumType.STRING)
    private Profissao profissao;

    private String habilidades;
}

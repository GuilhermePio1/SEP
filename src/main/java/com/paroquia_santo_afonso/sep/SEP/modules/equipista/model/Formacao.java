package com.paroquia_santo_afonso.sep.SEP.modules.equipista.model;

import lombok.Getter;

@Getter
public enum Formacao {
    NIVEL_FUNDAMENTAL("Nível Fundamental"),
    NIVEL_MEDIO("Nível Médio"),
    NIVEL_SUPERIOR_CURSANDO("Nível Superior cursando"),
    NIVEL_SUPERIOR_COMPLETO("Nível Superior completo"),
    ESPECIALIZACAO("Especialização"),
    MESTRADO("Mestrado"),
    DOUTORADO("Doutorado");

    private final String title;

    Formacao(String title) {
        this.title = title;
    }
}

package com.paroquia_santo_afonso.sep.SEP.domain.model;

public enum Ocupacao {
    APOSENTADO("Aposentado/a"),
    ASSALARIADO_CLT("Assalariado CLT"),
    AUTONOMO("Autônomo"),
    EMPRESARIO("Empresário"),
    ESTUDANTE("Estudante"),
    SERVIDOR_PUBLICO("Servidor Público"),
    OUTRA("Outra");

    private final String title;

    Ocupacao(String title) {
        this.title = title;
    }
}

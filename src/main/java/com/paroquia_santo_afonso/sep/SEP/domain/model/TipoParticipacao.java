package com.paroquia_santo_afonso.sep.SEP.domain.model;

public enum TipoParticipacao {
    CASAL_COORDENADOR("Casal Coordenador"),
    EQUIPISTA_COORDENADOR("Equipista Coordenador"),
    CASAL("Casal"),
    EQUIPISTA("Equipista");

    private final String title;

    TipoParticipacao(String title) {
        this.title = title;
    }
}

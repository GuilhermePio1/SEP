package com.paroquia_santo_afonso.sep.SEP.modules.equipista.model;

public enum TipoParticipacao {
    CASAL_COORDENADOR("Casal Coordenador"),
    EQUIPISTA_COORDENADOR("Equipista Coordenador"),
    CASAL("Casal"),
    EQUIPISTA("Equipista"),
    ENCONTRISTA("Encontrista");

    private final String title;

    TipoParticipacao(String title) {
        this.title = title;
    }
}

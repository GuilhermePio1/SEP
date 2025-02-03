package com.paroquia_santo_afonso.sep.SEP.domain.model;

public enum Sacramento {
    BATISMO("Batismo"),
    PRIMEIRA_EUCARISTIA("Primeira Eucaristia"),
    CRISMA("Crisma"),
    MATRIMONIO("Matrimônio"),
    ORDEM_DIACONAL("Ordem Diaconal");

    private final String title;

    Sacramento(String title) {
        this.title = title;
    }
}

package com.paroquia_santo_afonso.sep.SEP.modules.equipista.model;

import lombok.Getter;

@Getter
public enum EstadoCivil {
    CASADO_CIVIL("Casado/a apenas no civil"),
    CASADO_RELIGIOSO("Casado/a no religioso"),
    DIVORCIADO("Divorciado/a"),
    SEPARADO("Separado/a"),
    SOLTEIRO("Solteiro/a"),
    UNIAO_ESTAVEL("União Estável"),
    VIUVO("Viúvo/a");

    private final String title;

    EstadoCivil(String title) {
        this.title = title;
    }
}

package com.paroquia_santo_afonso.sep.SEP.modules.encontro.builder;

import com.paroquia_santo_afonso.sep.SEP.modules.encontro.dto.ListarEncontroEquipeDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.model.Encontro;

public class ListarEncontroEquipeBuilder {
    public static ListarEncontroEquipeDTO toListarEncontroEquipe(Encontro encontro) {
        return ListarEncontroEquipeDTO.builder()
                .id(encontro.getId())
                .nome(encontro.getNome())
                .build();
    }
}

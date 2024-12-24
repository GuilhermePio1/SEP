package com.paroquia_santo_afonso.sep.SEP.api.dto.builder;

import com.paroquia_santo_afonso.sep.SEP.api.dto.ListarEncontroDTO;
import com.paroquia_santo_afonso.sep.SEP.api.dto.ListarEncontroEquipeDTO;
import com.paroquia_santo_afonso.sep.SEP.domain.model.Encontro;

public class ListarEncontroEquipeBuilder {
    public static ListarEncontroEquipeDTO toListarEncontroEquipe(Encontro encontro) {
        return ListarEncontroEquipeDTO.builder()
                .id(encontro.getId())
                .nome(encontro.getNome())
                .build();
    }
}

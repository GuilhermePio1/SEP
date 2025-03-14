package com.paroquia_santo_afonso.sep.SEP.modules.equipista.builder;

import com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto.AreaAtuacaoDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.AreaAtuacao;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.Formacao;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.Ocupacao;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.Profissao;

public class AreaAtuacaoBuilder {
    public static AreaAtuacaoDTO toAreaAtuacaoDTO(AreaAtuacao areaAtuacao) {
        return AreaAtuacaoDTO.builder()
                .profissao(areaAtuacao.getProfissao().getTitle())
                .habilidades(areaAtuacao.getHabilidades())
                .ocupacao(areaAtuacao.getOcupacao().getTitle())
                .formacao(areaAtuacao.getFormacao().getTitle())
                .build();
    }

    public static AreaAtuacao toAreaAtuacao(AreaAtuacaoDTO dto) {
        return AreaAtuacao.builder()
                .formacao(mapFormacao(dto.getFormacao()))
                .profissao(mapProfissao(dto.getProfissao()))
                .ocupacao(mapOcupacao(dto.getOcupacao()))
                .habilidades(dto.getHabilidades())
                .build();
    }

    private static Formacao mapFormacao(String formacao) {
        if (formacao != null && !formacao.isEmpty())
            return Formacao.valueOf(formacao);

        return null;
    }

    private static Profissao mapProfissao(String profissao) {
        if (profissao != null && !profissao.isEmpty())
            return Profissao.valueOf(profissao);

        return null;
    }

    private static Ocupacao mapOcupacao(String ocupacao) {
        if (ocupacao != null && !ocupacao.isEmpty())
            return Ocupacao.valueOf(ocupacao);

        return null;
    }
}

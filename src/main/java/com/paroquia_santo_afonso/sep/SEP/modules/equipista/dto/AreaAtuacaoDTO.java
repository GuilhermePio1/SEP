package com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto;

import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.Formacao;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.Ocupacao;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.Profissao;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AreaAtuacaoDTO {
    private String formacao;
    private String ocupacao;
    private String profissao;
    private String habilidades;

    public Formacao getFormacaoEnum() {
        return formacao != null ? Formacao.valueOf(formacao) : null;
    }

    public Ocupacao getOcupacaoEnum() {
        return ocupacao != null ? Ocupacao.valueOf(ocupacao) : null;
    }

    public Profissao getProfissaoEnum() {
        return profissao != null ? Profissao.valueOf(profissao) : null;
    }
}

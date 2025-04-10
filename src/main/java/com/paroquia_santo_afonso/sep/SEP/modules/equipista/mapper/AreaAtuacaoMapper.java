package com.paroquia_santo_afonso.sep.SEP.modules.equipista.mapper;

import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.Formacao;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.Ocupacao;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.Profissao;
import org.springframework.stereotype.Component;

import com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto.AreaAtuacaoDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.AreaAtuacao;

import java.util.Optional;

@Component
public class AreaAtuacaoMapper {
    public AreaAtuacao toEntity(AreaAtuacaoDTO dto) {
        return AreaAtuacao.builder()
                .formacao(dto.getFormacaoEnum())
                .profissao(dto.getProfissaoEnum())
                .ocupacao(dto.getOcupacaoEnum())
                .habilidades(dto.getHabilidades())
                .build();
    }

    public AreaAtuacaoDTO toDTO(AreaAtuacao entity) {
        return AreaAtuacaoDTO.builder()
                .formacao(Optional.ofNullable(entity.getFormacao()).map(Formacao::getTitle).orElse(null))
                .profissao(Optional.ofNullable(entity.getProfissao()).map(Profissao::getTitle).orElse(null))
                .ocupacao(Optional.ofNullable(entity.getOcupacao()).map(Ocupacao::getTitle).orElse(null))
                .habilidades(entity.getHabilidades())
                .build();
    }
}

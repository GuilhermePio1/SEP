package com.paroquia_santo_afonso.sep.SEP.modules.equipista.mapper;

import java.time.Year;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.paroquia_santo_afonso.sep.SEP.modules.equipe.model.Equipe;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto.ParticipacaoEncontroRequestDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto.ParticipacaoEncontroResponseDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.ParticipacaoEncontro;

@Component
public class ParticipacaoEncontroMapper {
    public ParticipacaoEncontro toEntity(ParticipacaoEncontroRequestDTO dto) {
        return ParticipacaoEncontro.builder()
                .equipe(new Equipe(dto.getIdEquipe()))
                .ano(dto.getAno())
                .tipoParticipacao(dto.getTipoParticipacaoEnum())
                .build();
    }

    public ParticipacaoEncontroResponseDTO toResponseDTO(ParticipacaoEncontro entity) {
        return ParticipacaoEncontroResponseDTO.builder()
                .id(entity.getId())
                .nomeEncontro(entity.getEquipe().getEncontro().getNome())
                .idEquipe(entity.getEquipe().getId())
                .nomeEquipe(entity.getEquipe().getNome())
                .ano(Optional.ofNullable(entity.getAno()).map(Year::getValue).orElse(null))
                .tipoParticipacao(entity.getTipoParticipacao().getTitle())
                .build();
    }

    public void updateEntityFromDTO(ParticipacaoEncontro entity, ParticipacaoEncontroRequestDTO dto) {
        entity.setEquipe(new Equipe(dto.getIdEquipe()));
        entity.setAno(dto.getAno());
        entity.setTipoParticipacao(dto.getTipoParticipacaoEnum());
    }
}

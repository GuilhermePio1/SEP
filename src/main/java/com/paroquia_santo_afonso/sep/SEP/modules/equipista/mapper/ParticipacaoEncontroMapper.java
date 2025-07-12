package com.paroquia_santo_afonso.sep.SEP.modules.equipista.mapper;

import com.paroquia_santo_afonso.sep.SEP.modules.equipe.model.Equipe;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto.ParticipacaoEncontroRequestDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto.ParticipacaoEncontroResponseDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.ParticipacaoEncontro;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.Objects;
import java.util.Optional;

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
                .nomeEncontro(Objects.nonNull(entity.getEquipe()) ? entity.getEquipe().getEncontro().getNome() : null)
                .idEquipe(Objects.nonNull(entity.getEquipe()) ? entity.getEquipe().getId() : null)
                .nomeEquipe(Objects.nonNull(entity.getEquipe()) ? entity.getEquipe().getNome() : null)
                .ano(Optional.ofNullable(entity.getAno()).map(Year::getValue).orElse(null))
                .tipoParticipacao(Objects.nonNull(entity.getEquipe()) ? entity.getTipoParticipacao().getTitle() : null)
                .build();
    }

    public void updateEntityFromDTO(ParticipacaoEncontro entity, ParticipacaoEncontroRequestDTO dto) {
        entity.setEquipe(new Equipe(dto.getIdEquipe()));
        entity.setAno(dto.getAno());
        entity.setTipoParticipacao(dto.getTipoParticipacaoEnum());
    }
}

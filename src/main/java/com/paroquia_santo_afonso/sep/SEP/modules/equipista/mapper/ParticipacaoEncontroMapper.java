package com.paroquia_santo_afonso.sep.SEP.modules.equipista.mapper;

import com.paroquia_santo_afonso.sep.SEP.modules.equipe.model.Equipe;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto.ParticipacaoEncontroRequestDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto.ParticipacaoEncontroResponseDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.ParticipacaoEncontro;
import org.springframework.stereotype.Component;

import java.util.Objects;

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
        Equipe equipe = entity.getEquipe();

        return ParticipacaoEncontroResponseDTO.builder()
                .id(entity.getId())
                .nomeEncontro(Objects.nonNull(equipe) && Objects.nonNull(equipe.getEncontro()) ? equipe.getEncontro().getNome() : null)
                .idEquipe(Objects.nonNull(equipe) ? equipe.getId() : null)
                .nomeEquipe(Objects.nonNull(equipe) ? equipe.getNome() : null)
                .ano(Objects.nonNull(entity.getAno()) ? entity.getAno().getValue() : null)
                .tipoParticipacao(Objects.nonNull(entity.getTipoParticipacao()) ? entity.getTipoParticipacao().name() : null)
                .build();
    }

    public void updateEntityFromDTO(ParticipacaoEncontro entity, ParticipacaoEncontroRequestDTO dto) {
        entity.setEquipe(new Equipe(dto.getIdEquipe()));
        entity.setAno(dto.getAno());
        entity.setTipoParticipacao(dto.getTipoParticipacaoEnum());
    }
}

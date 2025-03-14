package com.paroquia_santo_afonso.sep.SEP.modules.equipista.builder;

import com.paroquia_santo_afonso.sep.SEP.modules.equipe.model.Equipe;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto.ParticipacaoEncontroDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.Equipista;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.ParticipacaoEncontro;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.TipoParticipacao;

public class ParticipacaoEncontroBuilder {
    public static ParticipacaoEncontroDTO toParticipacaoEncontroDTO(ParticipacaoEncontro participacaoEncontro) {
        return ParticipacaoEncontroDTO.builder()
                .id(participacaoEncontro.getId())
                .anoParticipacao(participacaoEncontro.getAno())
                .tipoParticipacao(participacaoEncontro.getTipoParticipacao() != null ? participacaoEncontro.getTipoParticipacao().getTitle() : null)
                .equipistaId(participacaoEncontro.getEquipista() != null ? participacaoEncontro.getEquipista().getId() : null)
                .equipeId(participacaoEncontro.getEquipe() != null ? participacaoEncontro.getEquipe().getId() : null)
                .encontroId(participacaoEncontro.getEquipe() != null ? participacaoEncontro.getEquipe().getEncontro().getId() : null)
                .nomeEncontro(participacaoEncontro.getEquipe() != null ? participacaoEncontro.getEquipe().getEncontro().getNome() : null)
                .nomeEquipe(participacaoEncontro.getEquipe() != null ? participacaoEncontro.getEquipe().getNome() : null)
                .build();
    }

    public static ParticipacaoEncontro toParticipacaoEncontro(ParticipacaoEncontroDTO participacaoEncontroDTO) {
        return ParticipacaoEncontro.builder()
                .id(participacaoEncontroDTO.getId())
                .tipoParticipacao(mapTipoParticipacao(participacaoEncontroDTO.getTipoParticipacao()))
                .ano(participacaoEncontroDTO.getAnoParticipacao())
                .equipe(new Equipe(participacaoEncontroDTO.getEquipeId()))
                .equipista(new Equipista(participacaoEncontroDTO.getEquipistaId()))
                .build();

    }

    private static TipoParticipacao mapTipoParticipacao(String tipoParticipacao) {
        if (tipoParticipacao != null && !tipoParticipacao.isEmpty())
            return TipoParticipacao.valueOf(tipoParticipacao);

        return null;
    }
}

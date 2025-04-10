package com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.TipoParticipacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipacaoEncontroRequestDTO {
    @NotNull(message = "A equipe é obrigatória.")
    private Long idEquipe;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Year ano;
    
    @NotBlank(message = "O tipo de participação é obrigatório.")
    private String tipoParticipacao;

    private String acaoParticipacaoEncontro;

    public TipoParticipacao getTipoParticipacaoEnum() {
        return tipoParticipacao != null ? TipoParticipacao.valueOf(tipoParticipacao) : null;
    }

    public AcaoParticipacaoEncontro getAcaoParticipacaoEncontroEnum() {
        return acaoParticipacaoEncontro != null ? AcaoParticipacaoEncontro.valueOf(acaoParticipacaoEncontro) : null;
    }
}

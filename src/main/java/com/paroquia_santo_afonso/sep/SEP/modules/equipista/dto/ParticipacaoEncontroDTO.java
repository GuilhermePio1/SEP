package com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.Year;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParticipacaoEncontroDTO {
    private Long id;
    private Long encontroId;
    private String nomeEncontro;
    private Long equipeId;
    private String nomeEquipe;
    private Long equipistaId;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Year anoParticipacao;

    private String tipoParticipacao;
}

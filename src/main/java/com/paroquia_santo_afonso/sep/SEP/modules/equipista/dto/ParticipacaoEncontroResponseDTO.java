package com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParticipacaoEncontroResponseDTO {
    private Long id;
    private String nomeEncontro;
    private Long idEquipe;
    private String nomeEquipe;
    private Integer ano;
    private String tipoParticipacao;
}

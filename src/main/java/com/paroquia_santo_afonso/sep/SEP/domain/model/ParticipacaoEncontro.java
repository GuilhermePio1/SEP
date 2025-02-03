package com.paroquia_santo_afonso.sep.SEP.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.Year;

@Entity
@Table(name = "participacoes_encontros")
@Data
@Builder
public class ParticipacaoEncontro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    @ManyToOne
    @JoinColumn(name = "equipista_id")
    private Equipista equipista;

    @NotBlank(message = "É preciso informar o ano.")
    private Year ano;

    @Enumerated(EnumType.STRING)
    private TipoParticipacao tipoParticipacao;

}

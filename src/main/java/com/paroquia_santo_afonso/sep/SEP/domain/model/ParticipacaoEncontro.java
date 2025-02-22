package com.paroquia_santo_afonso.sep.SEP.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Year;

@Entity
@Table(name = "participacoes_encontros", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"equipe_id", "equipista_id", "tipo_participacao"})
})
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ParticipacaoEncontro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name = "equipista_id")
    private Equipista equipista;

    private Year ano;

    @EqualsAndHashCode.Include
    @Enumerated(EnumType.STRING)
    private TipoParticipacao tipoParticipacao;

}

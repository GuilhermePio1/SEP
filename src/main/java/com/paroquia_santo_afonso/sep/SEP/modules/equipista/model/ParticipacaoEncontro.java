package com.paroquia_santo_afonso.sep.SEP.modules.equipista.model;

import com.paroquia_santo_afonso.sep.SEP.modules.equipe.model.Equipe;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
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
    @JoinColumn(name = "equipe_id", nullable = false)
    @NotNull(message = "A equipe e o encontro são obrigatórios.")
    private Equipe equipe;

    @EqualsAndHashCode.Include
    @ManyToOne
    @JoinColumn(name = "equipista_id", nullable = false)
    @NotNull(message = "O equipista é obrigatório.")
    private Equipista equipista;

    private Year ano;

    @EqualsAndHashCode.Include
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O tipo de participação é obrigatório.")
    @Column(nullable = false)
    private TipoParticipacao tipoParticipacao;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @PrePersist
    public void prePersist() {
        dataCriacao = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }
}

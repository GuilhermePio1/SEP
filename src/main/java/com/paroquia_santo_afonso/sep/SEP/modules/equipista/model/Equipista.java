package com.paroquia_santo_afonso.sep.SEP.modules.equipista.model;

import com.paroquia_santo_afonso.sep.SEP.modules.pastoral.model.Pastoral;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "equipistas")
@Data
@Builder
@ToString(exclude = {"pastorais", "participacoesEncontros"})
@EqualsAndHashCode(exclude = {"pastorais", "participacoesEncontros"})
@AllArgsConstructor
@NoArgsConstructor
public class Equipista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] foto;

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @Past
    @NotNull(message = "A data de nascimento é obrigatório.")
    private LocalDate dataNascimento;

    @Embedded
    private Endereco endereco;

    @NotBlank(message = "O número de telefone é obrigatório.")
    private String numeroTelefone;

    @Embedded
    private AreaAtuacao areaAtuacao;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O estado civil é obrigatório.")
    private EstadoCivil estadoCivil;

    private String filhos;

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(
            name = "equipista_pastoral",
            joinColumns = @JoinColumn(name = "equipista_id"),
            inverseJoinColumns = @JoinColumn(name = "pastoral_id"),
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = {"equipista_id", "pastoral_id"})
            }
    )
    private List<Pastoral> pastorais;

    @Enumerated(EnumType.STRING)
    private Sacramento sacramento;

    @OneToMany(mappedBy = "equipista")
    Set<ParticipacaoEncontro> participacoesEncontros;

}

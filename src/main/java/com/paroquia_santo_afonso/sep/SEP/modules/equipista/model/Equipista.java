package com.paroquia_santo_afonso.sep.SEP.modules.equipista.model;

import com.paroquia_santo_afonso.sep.SEP.common.base.model.FileBaseEntity;
import com.paroquia_santo_afonso.sep.SEP.modules.pastoral.model.Pastoral;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "equipistas")
@Data
@SuperBuilder
@ToString(exclude = {"pastorais", "participacoesEncontros"})
@EqualsAndHashCode(exclude = {"pastorais", "participacoesEncontros"}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Equipista extends FileBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    @Column(nullable = false)
    private String nome;

    @Past(message = "A data de nascimento precisa estar no passado.")
    @NotNull(message = "A data de nascimento é obrigatório.")
    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Embedded
    private Endereco endereco;

    @NotBlank(message = "O número de telefone é obrigatório.")
    @Column(nullable = false)
    @Pattern(regexp = "\\d{11}", message = "O número de telefone deve conter 11 dígitos")
    private String numeroTelefone;

    @Embedded
    private AreaAtuacao areaAtuacao;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O estado civil é obrigatório.")
    @Column(nullable = false)
    private EstadoCivil estadoCivil;

    private String filhos;

    @ManyToMany
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

    @OneToMany(mappedBy = "equipista", cascade = CascadeType.REMOVE)
    Set<ParticipacaoEncontro> participacoesEncontros;

    public Equipista(Long id) {
        this.setId(id);
    }
}

package com.paroquia_santo_afonso.sep.SEP.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "pastorais")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "equipista")
@ToString(exclude = {"equipista"})
public class Pastoral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @ManyToMany(mappedBy = "pastorais")
    private List<Equipista> equipista;

}

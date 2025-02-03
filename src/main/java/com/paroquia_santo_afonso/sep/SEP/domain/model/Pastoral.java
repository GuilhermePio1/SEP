package com.paroquia_santo_afonso.sep.SEP.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "pastorais")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pastoral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @ManyToMany(mappedBy = "pastorais")
    private List<Equipista> equipista;

}

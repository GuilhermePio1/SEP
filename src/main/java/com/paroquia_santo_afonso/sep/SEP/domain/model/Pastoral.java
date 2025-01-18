package com.paroquia_santo_afonso.sep.SEP.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "pastorais")
@Data
@Builder
public class Pastoral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    public Pastoral(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Pastoral() { }

}

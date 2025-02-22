package com.paroquia_santo_afonso.sep.SEP.modules.equipista.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    private String cep;

    private String logradouro;

    private String bairro;

    private String cidade;

    private String estado;

    private String numero;

    private String complemento;

}
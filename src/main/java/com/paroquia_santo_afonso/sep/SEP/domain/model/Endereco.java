package com.paroquia_santo_afonso.sep.SEP.domain.model;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Embeddable
public class Endereco {
    private String cep;

    private String logradouro;

    private String bairro;

    private String cidade;

    private String estado;

    private String numero;

    private String complemento;

}
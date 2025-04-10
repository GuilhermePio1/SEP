package com.paroquia_santo_afonso.sep.SEP.modules.equipista.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(
        regexp = "\\d{5}-\\d{3}|\\d{8}", 
        message = "O CEP deve estar no formato 00000-000 ou 00000000"
    )
    private String cep;

    private String logradouro;
    private String bairro;
    private String cidade;
    private String estado;
    private String numero;
    private String complemento;

}
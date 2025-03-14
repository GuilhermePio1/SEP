package com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {
    private String logradouro;
    private String cep;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String complemento;
}

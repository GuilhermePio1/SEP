package com.paroquia_santo_afonso.sep.SEP.modules.equipista.builder;

import com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto.EnderecoDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.Endereco;

public class EnderecoBuilder {
    public static EnderecoDTO toEnderecoDTO(Endereco endereco) {
        return EnderecoDTO.builder()
                .cep(endereco.getCep())
                .cidade(endereco.getCidade())
                .bairro(endereco.getBairro())
                .estado(endereco.getEstado())
                .numero(endereco.getNumero())
                .logradouro(endereco.getLogradouro())
                .complemento(endereco.getComplemento())
                .build();

    }

    public static Endereco toEndereco(EnderecoDTO enderecoDTO) {
        return Endereco.builder()
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .numero(enderecoDTO.getNumero())
                .bairro(enderecoDTO.getBairro())
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .logradouro(enderecoDTO.getLogradouro())
                .build();
    }
}

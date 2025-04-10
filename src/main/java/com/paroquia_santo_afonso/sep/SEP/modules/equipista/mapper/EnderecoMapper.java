package com.paroquia_santo_afonso.sep.SEP.modules.equipista.mapper;

import org.springframework.stereotype.Component;

import com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto.EnderecoDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.Endereco;

@Component
public class EnderecoMapper {
    public Endereco toEntity(EnderecoDTO dto) {
        return Endereco.builder()
                .cep(dto.getCep())
                .logradouro(dto.getLogradouro())
                .bairro(dto.getBairro())
                .cidade(dto.getCidade())
                .estado(dto.getEstado())
                .numero(dto.getNumero())
                .complemento(dto.getComplemento())
                .build();
    }

    public EnderecoDTO toDTO(Endereco entity) {
        return EnderecoDTO.builder()
                .cep(entity.getCep())
                .logradouro(entity.getLogradouro())
                .bairro(entity.getBairro())
                .cidade(entity.getCidade())
                .estado(entity.getEstado())
                .numero(entity.getNumero())
                .complemento(entity.getComplemento())
                .build();
    }
}

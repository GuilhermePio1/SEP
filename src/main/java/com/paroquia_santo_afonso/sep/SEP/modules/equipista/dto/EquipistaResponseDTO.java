package com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto;

import java.util.List;

import com.paroquia_santo_afonso.sep.SEP.common.base.dto.FileBaseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EquipistaResponseDTO extends FileBaseDTO {
    private Long id;
    private String nome;
    private String dataNascimento;
    private EnderecoDTO enderecoDTO;
    private String numeroTelefone;
    private AreaAtuacaoDTO areaAtuacaoDTO;
    private String estadoCivil;
    private String filhos;
    private List<PastoralDTO> pastorais;
    private String sacramento;
    private List<ParticipacaoEncontroResponseDTO> participacoesEncontro;
    
}

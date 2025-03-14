package com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paroquia_santo_afonso.sep.SEP.common.base.dto.FileBaseDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.pastoral.dto.PastoralDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class EquipistaDTO extends FileBaseDTO {
    private String nome;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private EnderecoDTO enderecoDTO;
    private String numeroTelefone;
    private AreaAtuacaoDTO areaAtuacaoDTO;
    private String estadoCivil;
    private String filhos;
    private String sacramento;
    private List<PastoralDTO> pastoraisDTO;
    private List<ParticipacaoEncontroDTO> participacoesEncontrosDTO;
}

package com.paroquia_santo_afonso.sep.SEP.modules.encontro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EncontroResponseDTO {
    private Long id;
    private String nome;
    private List<EquipeDTO> equipes;

    /**
     * Construtor para o SQLResultSetMapping "EncontroResponseDTOMapping"
     *
     * @param id   identificador do encontro
     * @param nome nome do encontro
     */
    public EncontroResponseDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}

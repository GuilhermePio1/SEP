package com.paroquia_santo_afonso.sep.SEP.modules.pastoral.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PastoralResponseDTO {
    private Long id;
    private String nome;
}
package com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PastoralDTO {
    private Long id;
    private String nome;
}

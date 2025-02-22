package com.paroquia_santo_afonso.sep.SEP.modules.pastoral.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PastoralDTO {
    private long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;
}

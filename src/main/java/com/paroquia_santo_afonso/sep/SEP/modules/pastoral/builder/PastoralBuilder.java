package com.paroquia_santo_afonso.sep.SEP.modules.pastoral.builder;

import com.paroquia_santo_afonso.sep.SEP.modules.pastoral.dto.PastoralDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.pastoral.model.Pastoral;

public class PastoralBuilder {
    public static PastoralDTO toPastoralDTO(Pastoral pastoral) {
        return PastoralDTO.builder()
                .id(pastoral.getId())
                .nome(pastoral.getNome())
                .build();
    }

    public static Pastoral toPastoral(PastoralDTO pastoralDTO) {
        return Pastoral.builder()
                .id(pastoralDTO.getId())
                .nome(pastoralDTO.getNome())
                .build();
    }
}

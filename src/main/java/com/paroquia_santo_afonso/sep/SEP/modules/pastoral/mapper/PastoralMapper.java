package com.paroquia_santo_afonso.sep.SEP.modules.pastoral.mapper;

import com.paroquia_santo_afonso.sep.SEP.modules.pastoral.dto.PastoralRequestDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.pastoral.dto.PastoralResponseDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.pastoral.model.Pastoral;
import org.springframework.stereotype.Component;

@Component
public class PastoralMapper {

    public Pastoral toEntity(PastoralRequestDTO dto) {
        return Pastoral.builder()
                .nome(dto.getNome())
                .build();
    }

    public PastoralResponseDTO toResponseDTO(Pastoral pastoral) {
        return PastoralResponseDTO.builder()
                .id(pastoral.getId())
                .nome(pastoral.getNome())
                .build();
    }

    public void updateEntityFromDTO(PastoralRequestDTO dto, Pastoral pastoral) {
        pastoral.setNome(dto.getNome());
    }

}

package com.paroquia_santo_afonso.sep.SEP.modules.equipista.repository;

import com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao.FiltroPaginacaoDto;
import com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao.FiltroPaginacaoResponse;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto.EquipistaResponseDTO;

public interface EquipistaCustomRepository {
    FiltroPaginacaoResponse<EquipistaResponseDTO> buscarPaginado(FiltroPaginacaoDto dto);
}

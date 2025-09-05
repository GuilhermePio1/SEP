package com.paroquia_santo_afonso.sep.SEP.modules.encontro.repository;

import com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao.FiltroPaginacaoDto;
import com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao.FiltroPaginacaoResponse;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.dto.EncontroResponseDTO;

public interface EncontroCustomRepository {
    FiltroPaginacaoResponse<EncontroResponseDTO> buscarPaginado(FiltroPaginacaoDto dto);
}
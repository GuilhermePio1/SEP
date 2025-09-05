package com.paroquia_santo_afonso.sep.SEP.modules.equipe.repository;

import com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao.FiltroPaginacaoDto;
import com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao.FiltroPaginacaoResponse;
import com.paroquia_santo_afonso.sep.SEP.modules.equipe.dto.EquipeResponseDTO;

public interface EquipeCustomRepository {
    FiltroPaginacaoResponse<EquipeResponseDTO> buscarPaginado(FiltroPaginacaoDto dto);
}
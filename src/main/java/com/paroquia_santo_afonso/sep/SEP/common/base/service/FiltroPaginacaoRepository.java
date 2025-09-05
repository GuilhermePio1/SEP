package com.paroquia_santo_afonso.sep.SEP.common.base.service;

import com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao.FiltroPaginacaoDto;
import com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao.FiltroPaginacaoResponse;
import org.springframework.data.repository.NoRepositoryBean;

public interface FiltroPaginacaoRepository<T> {
    FiltroPaginacaoResponse<T> buscarPaginado(FiltroPaginacaoDto dto);
}
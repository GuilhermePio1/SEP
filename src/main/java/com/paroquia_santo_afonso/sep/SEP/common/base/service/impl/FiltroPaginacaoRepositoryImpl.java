package com.paroquia_santo_afonso.sep.SEP.common.base.service.impl;

import com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao.FiltroPaginacaoDto;
import com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao.FiltroPaginacaoResponse;
import com.paroquia_santo_afonso.sep.SEP.common.base.service.FiltroPaginacaoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public class FiltroPaginacaoRepositoryImpl<T> implements FiltroPaginacaoRepository<T> {
    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public FiltroPaginacaoResponse<T> buscarPaginado(FiltroPaginacaoDto dto) {
        throw new UnsupportedOperationException("Método deve ser implementado pela classe filha");
    }
}
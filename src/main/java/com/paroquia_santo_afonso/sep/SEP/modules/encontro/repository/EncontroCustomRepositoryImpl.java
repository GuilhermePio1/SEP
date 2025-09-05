package com.paroquia_santo_afonso.sep.SEP.modules.encontro.repository;

import com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao.FiltroPaginacaoDto;
import com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao.FiltroPaginacaoResponse;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.dto.EncontroResponseDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class EncontroCustomRepositoryImpl implements EncontroCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public FiltroPaginacaoResponse<EncontroResponseDTO> buscarPaginado(FiltroPaginacaoDto dto) {
        String query = queryBuilder(dto, false).toString();

        List<EncontroResponseDTO> encontros = this.entityManager.createNativeQuery(query, "EncontroResponseDTOMapping")
                .setFirstResult(dto.getPage() * dto.getSize())
                .setMaxResults(dto.getSize())
                .getResultList();

        FiltroPaginacaoResponse<EncontroResponseDTO> response = new FiltroPaginacaoResponse<>();
        response.setContent(encontros);
        response.setTotalElements(quantidade(dto));
        return response;
    }

    private StringBuilder queryBuilder(FiltroPaginacaoDto dto, boolean isQuantidade) {
        StringBuilder query = new StringBuilder();

        if (isQuantidade) query.append("SELECT COUNT(*) FROM encontros e ");
        else query.append("SELECT * FROM encontros e ");

        return query;
    }

    private Long quantidade(FiltroPaginacaoDto filtroPaginacao) {
        StringBuilder query = queryBuilder(filtroPaginacao, true);
        return ((Number) this.entityManager.createNativeQuery(query.toString()).getSingleResult()).longValue();
    }

}
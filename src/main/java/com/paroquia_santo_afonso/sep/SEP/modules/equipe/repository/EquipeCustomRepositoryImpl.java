package com.paroquia_santo_afonso.sep.SEP.modules.equipe.repository;

import com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao.FiltroPaginacaoDto;
import com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao.FiltroPaginacaoResponse;
import com.paroquia_santo_afonso.sep.SEP.modules.equipe.dto.EquipeResponseDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class EquipeCustomRepositoryImpl implements EquipeCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public FiltroPaginacaoResponse<EquipeResponseDTO> buscarPaginado(FiltroPaginacaoDto dto) {
        String query = queryBuilder(dto, false).toString();

        List<EquipeResponseDTO> equipes = this.entityManager.createNativeQuery(query, "EquipeResponseDTOMapping")
                .setFirstResult(dto.getPage() * dto.getSize())
                .setMaxResults(dto.getSize())
                .getResultList();

        FiltroPaginacaoResponse<EquipeResponseDTO> response = new FiltroPaginacaoResponse<>();
        response.setContent(equipes);
        response.setTotalElements(quantidade(dto));
        return response;
    }

    private StringBuilder queryBuilder(FiltroPaginacaoDto dto, boolean isQuantidade) {
        StringBuilder query = new StringBuilder();

        if (isQuantidade) query.append("SELECT COUNT(*) FROM equipes e ");
        else query.append("SELECT * FROM equipes e ");

        return query;
    }

    private Long quantidade(FiltroPaginacaoDto filtroPaginacao) {
        StringBuilder query = queryBuilder(filtroPaginacao, true);
        return ((Number) this.entityManager.createNativeQuery(query.toString()).getSingleResult()).longValue();
    }
}
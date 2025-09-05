package com.paroquia_santo_afonso.sep.SEP.modules.equipista.repository;

import com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao.FiltroPaginacaoDto;
import com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao.FiltroPaginacaoResponse;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto.EquipistaResponseDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class EquipistaCustomRepositoryImpl implements EquipistaCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public FiltroPaginacaoResponse<EquipistaResponseDTO> buscarPaginado(FiltroPaginacaoDto dto) {
        String query = queryBuilder(dto, false).toString();

        List<EquipistaResponseDTO> equipistas = this.entityManager.createNativeQuery(query, "EquipistaResponseDTOMapping")
                .setFirstResult(dto.getPage() * dto.getSize())
                .setMaxResults(dto.getSize())
                .getResultList();

        FiltroPaginacaoResponse<EquipistaResponseDTO> response = new FiltroPaginacaoResponse<>();
        response.setContent(equipistas);
        response.setTotalElements(quantidade(dto));
        return response;
    }

    private StringBuilder queryBuilder(FiltroPaginacaoDto dto, boolean isQuantidade) {
        StringBuilder query = new StringBuilder();

        if (isQuantidade) query.append("SELECT COUNT(*) FROM equipistas e ");
        else query.append("SELECT * FROM equipistas e ");

        return query;
    }

    private Long quantidade(FiltroPaginacaoDto filtroPaginacao) {
        StringBuilder query = queryBuilder(filtroPaginacao, true);
        return ((Number) this.entityManager.createNativeQuery(query.toString()).getSingleResult()).longValue();
    }

}

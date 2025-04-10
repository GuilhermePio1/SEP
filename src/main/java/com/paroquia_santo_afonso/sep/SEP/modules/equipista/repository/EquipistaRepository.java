package com.paroquia_santo_afonso.sep.SEP.modules.equipista.repository;

import com.paroquia_santo_afonso.sep.SEP.common.base.repository.FileRepository;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.projection.EquipistaProjection;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.Equipista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface EquipistaRepository extends FileRepository<Equipista, Long> {
    @EntityGraph(attributePaths = {"pastorais", "participacoesEncontros"})
    Optional<Equipista> findByNomeAndNumeroTelefoneAndDataNascimento(String nome, String numeroTelefone, LocalDate dataNascimento);

    Page<EquipistaProjection> findAllProjectedBy(Pageable pageable);
}

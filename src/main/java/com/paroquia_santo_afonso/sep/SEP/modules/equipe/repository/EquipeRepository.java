package com.paroquia_santo_afonso.sep.SEP.modules.equipe.repository;

import com.paroquia_santo_afonso.sep.SEP.common.base.repository.FileRepository;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.model.Encontro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.paroquia_santo_afonso.sep.SEP.modules.equipe.model.Equipe;

import java.util.Optional;

@Repository
public interface EquipeRepository extends FileRepository<Equipe, Long> {
    Optional<Equipe> findByEncontroAndNome(Encontro encontro, String nome);
    Page<Equipe> findAllProjectedBy(Pageable pageable);
}

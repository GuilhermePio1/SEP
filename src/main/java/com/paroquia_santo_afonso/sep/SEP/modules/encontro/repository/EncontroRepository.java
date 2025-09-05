package com.paroquia_santo_afonso.sep.SEP.modules.encontro.repository;

import com.paroquia_santo_afonso.sep.SEP.modules.encontro.projection.EncontroProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paroquia_santo_afonso.sep.SEP.modules.encontro.model.Encontro;

import java.util.Optional;

@Repository
public interface EncontroRepository extends JpaRepository<Encontro, Long>, EncontroCustomRepository {
    Page<EncontroProjection> findAllProjectedBy(Pageable pageable);
    Optional<Encontro> findByNome(String nome);
}

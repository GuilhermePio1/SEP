package com.paroquia_santo_afonso.sep.SEP.modules.pastoral.repository;

import com.paroquia_santo_afonso.sep.SEP.modules.pastoral.model.Pastoral;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PastoralRepository extends JpaRepository<Pastoral, Long> {
    Optional<Pastoral> findByNome(String nome);
    Page<Pastoral> findAllProjectBy(Pageable pageable);
}

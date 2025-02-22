package com.paroquia_santo_afonso.sep.SEP.domain.repository;

import com.paroquia_santo_afonso.sep.SEP.domain.model.Pastoral;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PastoralRepository extends JpaRepository<Pastoral, Long> {
    Optional<Pastoral> findByNome(String nome);
}

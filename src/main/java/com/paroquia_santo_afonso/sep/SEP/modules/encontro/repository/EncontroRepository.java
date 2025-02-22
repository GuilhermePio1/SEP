package com.paroquia_santo_afonso.sep.SEP.modules.encontro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paroquia_santo_afonso.sep.SEP.modules.encontro.model.Encontro;

import java.util.Optional;

@Repository
public interface EncontroRepository extends JpaRepository<Encontro, Long>{
    Optional<Encontro> findByNome(String nome);
}

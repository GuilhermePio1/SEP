package com.paroquia_santo_afonso.sep.SEP.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paroquia_santo_afonso.sep.SEP.domain.model.Encontro;

@Repository
public interface EncontroRepository extends JpaRepository<Encontro, Long>{

}

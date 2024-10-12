package com.paroquia_santo_afonso.sep.SEP.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paroquia_santo_afonso.sep.SEP.domain.model.Pasta;

@Repository
public interface PastaRepository extends JpaRepository<Pasta, Long>{

}

package com.paroquia_santo_afonso.sep.SEP.domain.repository;

import com.paroquia_santo_afonso.sep.SEP.domain.model.Equipista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipistaRepository extends JpaRepository<Equipista, Long> {
}

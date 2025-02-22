package com.paroquia_santo_afonso.sep.SEP.modules.equipe.repository;

import com.paroquia_santo_afonso.sep.SEP.modules.encontro.model.Encontro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paroquia_santo_afonso.sep.SEP.modules.equipe.model.Equipe;

import java.util.Optional;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long>{
    Optional<Equipe> findByEncontroAndNome(Encontro encontro, String nome);
}

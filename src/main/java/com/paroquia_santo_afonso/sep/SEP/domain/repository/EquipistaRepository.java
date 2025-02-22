package com.paroquia_santo_afonso.sep.SEP.domain.repository;

import com.paroquia_santo_afonso.sep.SEP.domain.model.Equipista;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface EquipistaRepository extends JpaRepository<Equipista, Long> {
    @EntityGraph(attributePaths = {"pastorais", "participacoesEncontros"})
    Optional<Equipista> findByNomeAndNumeroTelefoneAndDataNascimento(String nome, String numeroTelefone, LocalDate dataNascimento);
}

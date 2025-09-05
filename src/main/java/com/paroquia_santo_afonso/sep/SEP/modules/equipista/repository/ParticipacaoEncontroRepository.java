package com.paroquia_santo_afonso.sep.SEP.modules.equipista.repository;

import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.ParticipacaoEncontro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParticipacaoEncontroRepository extends JpaRepository<ParticipacaoEncontro, Long> {
    Optional<ParticipacaoEncontro> findFirstByEquipistaIdAndEquipeIdOrderByDataAtualizacao(Long idEquipista, Long idEquipe);
}

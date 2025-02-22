package com.paroquia_santo_afonso.sep.SEP.modules.equipista.repository;


import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.PlanilhaSyncMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanilhaSyncMetadataRepository extends JpaRepository<PlanilhaSyncMetadata, Long> {
    Optional<PlanilhaSyncMetadata> findBySpreadsheetId(String spreadsheetId);
}

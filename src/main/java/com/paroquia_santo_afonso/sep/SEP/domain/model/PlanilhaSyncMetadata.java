package com.paroquia_santo_afonso.sep.SEP.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "planilha_sync_metadata")
@Data
public class PlanilhaSyncMetadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String spreadsheetId;

    private String checksum;

    private LocalDateTime updateAt;
}

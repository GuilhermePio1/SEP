package com.paroquia_santo_afonso.sep.SEP.common.base.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
public abstract class FileBaseEntity {
    private String fileName;
    private String fileType;

    @Lob
    private byte[] data;

}

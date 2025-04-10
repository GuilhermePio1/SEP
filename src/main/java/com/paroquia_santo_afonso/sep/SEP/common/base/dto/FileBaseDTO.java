package com.paroquia_santo_afonso.sep.SEP.common.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class FileBaseDTO {
    private String fileName;
    private String fileType;
    private byte[] fileData;
}

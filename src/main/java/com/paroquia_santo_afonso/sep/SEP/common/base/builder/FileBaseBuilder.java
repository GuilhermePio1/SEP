package com.paroquia_santo_afonso.sep.SEP.common.base.builder;

import com.paroquia_santo_afonso.sep.SEP.common.base.dto.FileBaseDTO;
import com.paroquia_santo_afonso.sep.SEP.common.base.model.FileBaseEntity;

public interface FileBaseBuilder<T extends FileBaseDTO, U extends FileBaseEntity> {
    U toEntity(T dto);
    T toDTO(U entity);
}

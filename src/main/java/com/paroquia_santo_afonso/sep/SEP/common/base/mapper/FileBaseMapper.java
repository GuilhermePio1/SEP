package com.paroquia_santo_afonso.sep.SEP.common.base.mapper;

import com.paroquia_santo_afonso.sep.SEP.common.base.dto.FileBaseDTO;
import com.paroquia_santo_afonso.sep.SEP.common.base.model.FileBaseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileBaseMapper<T extends FileBaseEntity, RQ extends FileBaseDTO, RS> {
    T toEntity(RS dto, MultipartFile file);
    RQ toResponseDTO(T entity);
    void updateEntityFromDTO(T entity, RS dto, MultipartFile file);
}

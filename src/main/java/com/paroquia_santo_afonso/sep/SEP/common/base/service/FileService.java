package com.paroquia_santo_afonso.sep.SEP.common.base.service;

import com.paroquia_santo_afonso.sep.SEP.common.base.dto.FileBaseDTO;
import org.springframework.web.multipart.MultipartFile;

public interface FileService<T extends FileBaseDTO> {
    T saveEntityWithFile(T dto, MultipartFile file);
    T getEntityWithFile(Long id);
}

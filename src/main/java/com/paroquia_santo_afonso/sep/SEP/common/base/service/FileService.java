package com.paroquia_santo_afonso.sep.SEP.common.base.service;

import com.paroquia_santo_afonso.sep.SEP.common.base.dto.FileBaseDTO;
import com.paroquia_santo_afonso.sep.SEP.common.base.dto.FileDownloadDTO;
import org.springframework.web.multipart.MultipartFile;

public interface FileService<RQ extends FileBaseDTO, RS> {
    RQ saveEntityWithFile(RS dto, MultipartFile multipartFile);
    RQ findEntityWithFileById(Long id);
    RQ updateEntityWithFile(RS dto, Long id,MultipartFile multipartFile);
    FileDownloadDTO downloadFile(Long id);
}

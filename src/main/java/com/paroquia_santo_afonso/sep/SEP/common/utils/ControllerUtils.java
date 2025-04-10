package com.paroquia_santo_afonso.sep.SEP.common.utils;

import com.paroquia_santo_afonso.sep.SEP.common.base.dto.FileDownloadDTO;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public class ControllerUtils {
    private static final String CONTENT_DISPOSITION_FORMAT = "attachment; filename=\"%s\"";

    public static ResponseEntity<Resource> createFileResponse(FileDownloadDTO fileDownload) {
        return ResponseEntity.ok()
                .contentType(fileDownload.mediaType())
                .contentLength(fileDownload.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        String.format(CONTENT_DISPOSITION_FORMAT, fileDownload.fileName()))
                .body(fileDownload.resource());

    }
}

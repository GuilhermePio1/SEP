package com.paroquia_santo_afonso.sep.SEP.common.base.dto;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

public record FileDownloadDTO(MediaType mediaType, int contentLength, String fileName, Resource resource) {
}

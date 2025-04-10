package com.paroquia_santo_afonso.sep.SEP.common.base.service.impl;

import com.paroquia_santo_afonso.sep.SEP.common.base.dto.FileBaseDTO;
import com.paroquia_santo_afonso.sep.SEP.common.base.dto.FileDownloadDTO;
import com.paroquia_santo_afonso.sep.SEP.common.base.mapper.FileBaseMapper;
import com.paroquia_santo_afonso.sep.SEP.common.base.model.FileBaseEntity;
import com.paroquia_santo_afonso.sep.SEP.common.base.projection.FileProjection;
import com.paroquia_santo_afonso.sep.SEP.common.base.repository.FileRepository;
import com.paroquia_santo_afonso.sep.SEP.common.base.service.FileService;
import com.paroquia_santo_afonso.sep.SEP.common.exception.ArquivoNotFoundException;
import com.paroquia_santo_afonso.sep.SEP.common.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public abstract class FileServiceImpl<T extends FileBaseEntity, U extends FileBaseMapper<T, RQ, RS>, V extends FileRepository<T, Long>, RQ extends FileBaseDTO, RS> implements FileService<RQ, RS> {
    private static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";
    
    protected final U mapper;
    protected final V repository;

    @Override
    @Transactional
    public RQ saveEntityWithFile(RS dto, MultipartFile multipartFile) {
        T entity = mapper.toEntity(dto, multipartFile);
        T entitySave = repository.save(entity);
        return mapper.toResponseDTO(entitySave);
    }

    @Override
    @Transactional(readOnly = true)
    public RQ findEntityWithFileById(Long id) {
        return repository.findById(id)
                .map(mapper::toResponseDTO)
                .orElseThrow(() -> createNotFoundException(id));
    }

    @Override
    @Transactional
    public RQ updateEntityWithFile(RS dto, Long id, MultipartFile file) {
        T entity = repository.findById(id)
                .orElseThrow(() -> createNotFoundException(id));

        mapper.updateEntityFromDTO(entity, dto, file);
        T entityUpdated = repository.save(entity);
        return mapper.toResponseDTO(entityUpdated);
    }

    @Override
    @Transactional(readOnly = true)
    public FileDownloadDTO downloadFile(Long id) {
        FileProjection fileProjection = repository.findFileProjectionById(id)
                .orElseThrow(() -> createNotFoundException(id));

        validateFileData(fileProjection);

        ByteArrayResource resource = createByteArrayResource(fileProjection);
        MediaType mediaType = determineMediaType(fileProjection.getFileType());

        return new FileDownloadDTO(
                mediaType,
                fileProjection.getData().length,
                fileProjection.getFileName(),
                resource
        );
    }

    private void validateFileData(FileProjection fileProjection) {
        if (fileProjection.getData() == null || fileProjection.getData().length == 0) {
            throw new ArquivoNotFoundException("O arquivo solicitado está vazio ou corrompido");
        }
    }

    private ByteArrayResource createByteArrayResource(FileProjection fileProjection) {
        return new ByteArrayResource(fileProjection.getData()) {
            @Override
            public String getFilename() {
                return fileProjection.getFileName();
            }
        };
    }

    private MediaType determineMediaType(String fileType) {
        return MediaType.parseMediaType(
                Optional.ofNullable(fileType)
                        .filter(type -> !type.isEmpty())
                        .orElse(DEFAULT_CONTENT_TYPE)
        );
    }

    protected abstract ResourceNotFoundException createNotFoundException(Long id);
}

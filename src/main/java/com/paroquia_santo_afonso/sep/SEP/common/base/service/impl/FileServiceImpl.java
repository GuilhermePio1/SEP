package com.paroquia_santo_afonso.sep.SEP.common.base.service.impl;

import com.paroquia_santo_afonso.sep.SEP.common.base.builder.FileBaseBuilder;
import com.paroquia_santo_afonso.sep.SEP.common.base.dto.FileBaseDTO;
import com.paroquia_santo_afonso.sep.SEP.common.base.model.FileBaseEntity;
import com.paroquia_santo_afonso.sep.SEP.common.base.repository.FileRepository;
import com.paroquia_santo_afonso.sep.SEP.common.base.service.FileService;
import com.paroquia_santo_afonso.sep.SEP.common.exception.EntidadeNaoEncontradaException;
import com.paroquia_santo_afonso.sep.SEP.common.exception.SaveFileWithEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public abstract class FileServiceImpl<T extends FileBaseDTO, U extends FileBaseEntity, V extends FileBaseBuilder<T, U>> implements FileService<T> {
    private final FileRepository<U, Long> fileRepository;
    private final V fileBuilder;

    @Override
    public T saveEntityWithFile(T dto, MultipartFile file) {
        try {
            if (file != null) {
                dto.setFileName(file.getOriginalFilename());
                dto.setFileType(file.getContentType());
                dto.setFileData(file.getBytes());
            }

            return fileBuilder.toDTO(fileRepository.save(fileBuilder.toEntity(dto)));
        }catch (Exception e) {
            throw new SaveFileWithEntityException("Falha ao salvar entidade com arquivo: ", e);
        }
    }

    @Override
    public T getEntityWithFile(Long id) {
        return fileBuilder.toDTO(fileRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Não foi encontrada a entidade com o id: " + id)));
    }
}

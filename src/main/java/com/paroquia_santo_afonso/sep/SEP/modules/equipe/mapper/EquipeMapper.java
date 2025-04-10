package com.paroquia_santo_afonso.sep.SEP.modules.equipe.mapper;

import com.paroquia_santo_afonso.sep.SEP.common.base.mapper.FileBaseMapper;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.model.Encontro;
import com.paroquia_santo_afonso.sep.SEP.modules.equipe.dto.EncontroDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.equipe.dto.EquipeResponseDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.equipe.dto.EquipeRequestDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.equipe.model.Equipe;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Component
public class EquipeMapper implements FileBaseMapper<Equipe, EquipeResponseDTO, EquipeRequestDTO> {

    @Override
    public Equipe toEntity(EquipeRequestDTO dto, MultipartFile file) {
        return Equipe.builder()
                .nome(dto.getNome())
                .encontro(new Encontro(dto.getEncontroId()))
                .fileName(Optional.ofNullable(file).map(MultipartFile::getOriginalFilename).orElse(null))
                .fileType(Optional.ofNullable(file).map(MultipartFile::getContentType).orElse(null))
                .data(Optional.ofNullable(file).map(f -> {
                    try {
                        return f.getBytes();
                    } catch (IOException e) {
                        return null;
                    }
                }).orElse(null))
                .build();
    }

    @Override
    public EquipeResponseDTO toResponseDTO(Equipe equipe) {
        return EquipeResponseDTO.builder()
                .id(equipe.getId())
                .nome(equipe.getNome())
                .encontro(toEncontroDTO(equipe.getEncontro()))
                .fileName(equipe.getFileName())
                .fileType(equipe.getFileType())
                .fileData(equipe.getData())
                .build();
    }

    @Override
    public void updateEntityFromDTO(Equipe equipe, EquipeRequestDTO dto, MultipartFile file) {
        equipe.setNome(dto.getNome());
        equipe.setEncontro(new Encontro(dto.getEncontroId()));
        equipe.setFileName(Optional.ofNullable(file).map(MultipartFile::getOriginalFilename).orElse(null));
        equipe.setFileType(Optional.ofNullable(file).map(MultipartFile::getContentType).orElse(null));
        equipe.setData(Optional.ofNullable(file).map(f -> {
            try {
                return f.getBytes();
            } catch (IOException e) {
                return null;
            }
        }).orElse(null));
    }

    private EncontroDTO toEncontroDTO(Encontro encontro) {
        return EncontroDTO.builder()
                .id(encontro.getId())
                .nome(encontro.getNome())
                .build();
    }
}

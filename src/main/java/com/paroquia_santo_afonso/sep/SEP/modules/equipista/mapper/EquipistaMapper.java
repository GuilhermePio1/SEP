package com.paroquia_santo_afonso.sep.SEP.modules.equipista.mapper;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.Sacramento;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.paroquia_santo_afonso.sep.SEP.common.base.mapper.FileBaseMapper;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.Equipista;
import com.paroquia_santo_afonso.sep.SEP.modules.pastoral.model.Pastoral;

import lombok.RequiredArgsConstructor;

import com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto.EquipistaRequestDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto.EquipistaResponseDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto.PastoralDTO;

@Component
@RequiredArgsConstructor
public class EquipistaMapper implements FileBaseMapper<Equipista, EquipistaResponseDTO, EquipistaRequestDTO> {

    private final EnderecoMapper enderecoMapper;
    private final AreaAtuacaoMapper areaAtuacaoMapper;
    private final ParticipacaoEncontroMapper participacaoEncontroMapper;
    
    @Override
    public Equipista toEntity(EquipistaRequestDTO dto, MultipartFile file) {
        return Equipista.builder()
                .nome(dto.getNome())
                .dataNascimento(dto.getDataNascimento())
                .endereco(Optional.ofNullable(dto.getEndereco()).map(enderecoMapper::toEntity).orElse(null))
                .numeroTelefone(dto.getNumeroTelefone())
                .areaAtuacao(Optional.ofNullable(dto.getAreaAtuacao()).map(areaAtuacaoMapper::toEntity).orElse(null))
                .estadoCivil(dto.getEstadoCivilEnum())
                .filhos(dto.getFilhos())
                .pastorais(Optional.ofNullable(dto.getIdPastorais())
                    .map(idPastorais -> idPastorais.stream()
                        .map(Pastoral::new)
                        .collect(Collectors.toList()))
                    .orElse(null))
                .sacramento(dto.getSacramentoEnum())
                .participacoesEncontros(Optional.ofNullable(dto.getParticipacoesEncontro())
                    .map(participacoes -> participacoes.stream()
                        .map(participacaoEncontroMapper::toEntity)
                        .collect(Collectors.toSet()))
                    .orElse(null))
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
    public EquipistaResponseDTO toResponseDTO(Equipista entity) {
        return EquipistaResponseDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .dataNascimento(entity.getDataNascimento().toString())
                .enderecoDTO(Optional.ofNullable(entity.getEndereco()).map(enderecoMapper::toDTO).orElse(null))
                .numeroTelefone(entity.getNumeroTelefone())
                .areaAtuacaoDTO(Optional.ofNullable(entity.getAreaAtuacao()).map(areaAtuacaoMapper::toDTO).orElse(null))
                .estadoCivil(entity.getEstadoCivil().name())
                .filhos(entity.getFilhos())
                .pastorais(Optional.ofNullable(entity.getPastorais()).map(this::mapPastoraisToDTO).orElse(null))
                .sacramento(Optional.ofNullable(entity.getSacramento()).map(Sacramento::name).orElse(null))
                .participacoesEncontro(Optional.ofNullable(entity.getParticipacoesEncontros())
                    .map(participacoes -> participacoes.stream()
                        .map(participacaoEncontroMapper::toResponseDTO)
                        .collect(Collectors.toList()))
                    .orElse(null))
                .fileName(entity.getFileName())
                .fileType(entity.getFileType())
                .fileData(entity.getData())
                .build();
    }

    @Override
    public void updateEntityFromDTO(Equipista entity, EquipistaRequestDTO dto, MultipartFile file) {
        entity.setNome(dto.getNome());
        entity.setDataNascimento(dto.getDataNascimento());
        entity.setEndereco(Optional.ofNullable(dto.getEndereco()).map(enderecoMapper::toEntity).orElse(null));
        entity.setNumeroTelefone(dto.getNumeroTelefone());
        entity.setAreaAtuacao(Optional.ofNullable(dto.getAreaAtuacao()).map(areaAtuacaoMapper::toEntity).orElse(null));
        entity.setEstadoCivil(dto.getEstadoCivilEnum());
        entity.setFilhos(dto.getFilhos());
        entity.setPastorais(Optional.ofNullable(dto.getIdPastorais())
                .map(idPastorais -> idPastorais.stream()
                        .map(Pastoral::new)
                        .collect(Collectors.toList()))
                .orElse(null));
        entity.setSacramento(dto.getSacramentoEnum());
        entity.setParticipacoesEncontros(Optional.ofNullable(dto.getParticipacoesEncontro())
                .map(participacoes -> participacoes.stream()
                        .map(participacaoEncontroMapper::toEntity)
                        .collect(Collectors.toSet()))
                .orElse(null));
        entity.setFileName(Optional.ofNullable(file).map(MultipartFile::getOriginalFilename).orElse(null));
        entity.setFileType(Optional.ofNullable(file).map(MultipartFile::getContentType).orElse(null));
        entity.setData(Optional.ofNullable(file).map(f -> {
            try {
                return f.getBytes();
            } catch (IOException e) {
                return null;
            }
        }).orElse(null));
    }
    
    private List<PastoralDTO> mapPastoraisToDTO(List<Pastoral> pastorais) {
        return pastorais.stream()
                .map(this::mapPastoralToDTO)
                .collect(Collectors.toList());
    }

    private PastoralDTO mapPastoralToDTO(Pastoral pastoral) {
        return PastoralDTO.builder()
                .id(pastoral.getId())
                .nome(pastoral.getNome())
                .build();
    }
}

package com.paroquia_santo_afonso.sep.SEP.modules.equipista.builder;

import com.paroquia_santo_afonso.sep.SEP.common.base.builder.FileBaseBuilder;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto.EquipistaDTO;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.Equipista;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.EstadoCivil;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.Sacramento;
import com.paroquia_santo_afonso.sep.SEP.modules.pastoral.builder.PastoralBuilder;
import org.springframework.stereotype.Component;

@Component
public class EquipistaBuilder implements FileBaseBuilder<EquipistaDTO, Equipista> {

    @Override
    public Equipista toEntity(EquipistaDTO dto) {
        return Equipista.builder()
                .areaAtuacao(dto.getAreaAtuacaoDTO() != null ?
                        AreaAtuacaoBuilder.toAreaAtuacao(dto.getAreaAtuacaoDTO()) : null)
                .dataNascimento(dto.getDataNascimento())
                .nome(dto.getNome())
                .filhos(dto.getFilhos())
                .endereco(dto.getEnderecoDTO() != null ?
                        EnderecoBuilder.toEndereco(dto.getEnderecoDTO()) : null)
                .numeroTelefone(dto.getNumeroTelefone())
                .estadoCivil(mapEstadoCivil(dto.getEstadoCivil()))
                .sacramento(mapSacramento(dto.getSacramento()))
                .id(dto.getId())
                .fileName(dto.getFileName())
                .fileType(dto.getFileType())
                .data(dto.getFileData())
                .pastorais(dto.getPastoraisDTO() != null ?
                        dto.getPastoraisDTO().stream()
                                .map(PastoralBuilder::toPastoral).toList() : null)
                .build();
    }

    @Override
    public EquipistaDTO toDTO(Equipista entity) {
        return EquipistaDTO.builder()
                .areaAtuacaoDTO(entity.getAreaAtuacao() != null ? AreaAtuacaoBuilder.toAreaAtuacaoDTO(entity.getAreaAtuacao()) : null)
                .nome(entity.getNome())
                .dataNascimento(entity.getDataNascimento())
                .sacramento(entity.getSacramento() != null ? entity.getSacramento().getTitle() : null)
                .enderecoDTO(entity.getEndereco() != null ? EnderecoBuilder.toEnderecoDTO(entity.getEndereco()) : null)
                .participacoesEncontrosDTO(entity.getParticipacoesEncontros() != null ?
                        entity.getParticipacoesEncontros().stream()
                                .map(ParticipacaoEncontroBuilder::toParticipacaoEncontroDTO).toList(): null)
                .filhos(entity.getFilhos())
                .numeroTelefone(entity.getNumeroTelefone())
                .pastoraisDTO(entity.getPastorais() != null ? entity.getPastorais().stream()
                        .map(PastoralBuilder::toPastoralDTO).toList() : null)
                .estadoCivil(entity.getEstadoCivil() != null ? entity.getEstadoCivil().getTitle() : null)
                .id(entity.getId())
                .fileName(entity.getFileName())
                .fileType(entity.getFileType())
                .fileData(entity.getData())
                .build();
    }

    private static EstadoCivil mapEstadoCivil(String estadoCivil) {
        if (estadoCivil != null && !estadoCivil.isEmpty())
            return EstadoCivil.valueOf(estadoCivil);

        return null;
    }

    private static Sacramento mapSacramento(String sacramento) {
        if (sacramento != null && !sacramento.isEmpty())
            return Sacramento.valueOf(sacramento);

        return null;
    }

}

package com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiltroPaginacaoDto {
    private int page;
    private int size;
    private List<String> colunas;
    private List<FiltroPaginacaoItemDto> filters;
}


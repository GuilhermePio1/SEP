package com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FiltroPaginacaoItemDto {
    private String attribute;
    private String operator;
    private String type;
    private List<String> values;
    private String origem;
    private String order;
    private String header;
}


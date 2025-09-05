package com.paroquia_santo_afonso.sep.SEP.common.base.dto.paginacao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiltroPaginacaoResponse<T> {
    private List<T> content;
    private Long totalElements;
}

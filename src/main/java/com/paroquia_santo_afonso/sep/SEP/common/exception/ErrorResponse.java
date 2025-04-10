package com.paroquia_santo_afonso.sep.SEP.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private Map<String, String> erros;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}

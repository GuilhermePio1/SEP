package com.paroquia_santo_afonso.sep.SEP.common.exception;

public class ArquivoNotFoundException extends ResourceNotFoundException {
    public ArquivoNotFoundException(Long id) {
        super("Arquivo", "id", id);
    }

    public ArquivoNotFoundException(String message) {
        super(message);
    }
}

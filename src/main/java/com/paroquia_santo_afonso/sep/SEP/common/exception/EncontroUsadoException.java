package com.paroquia_santo_afonso.sep.SEP.common.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class EncontroUsadoException extends DataIntegrityViolationException {
    public EncontroUsadoException(String message) {
        super(message);
    }
}

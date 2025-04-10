package com.paroquia_santo_afonso.sep.SEP.common.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class EquipeUsadaException extends DataIntegrityViolationException {
    public EquipeUsadaException(String message) {
        super(message);
    }
}

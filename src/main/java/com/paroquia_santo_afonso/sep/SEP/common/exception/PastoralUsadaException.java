package com.paroquia_santo_afonso.sep.SEP.common.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class PastoralUsadaException extends DataIntegrityViolationException {
    public PastoralUsadaException(String message) {
        super(message);
    }
}

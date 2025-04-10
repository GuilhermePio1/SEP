package com.paroquia_santo_afonso.sep.SEP.common.exception;

public class EquipeNotFoundException extends ResourceNotFoundException {
    public EquipeNotFoundException(Long id) {
        super("Equipe", "id", id);
    }

    public EquipeNotFoundException(String message) {
        super(message);
    }
}

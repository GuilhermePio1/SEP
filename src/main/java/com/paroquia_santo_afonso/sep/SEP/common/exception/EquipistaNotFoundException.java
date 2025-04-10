package com.paroquia_santo_afonso.sep.SEP.common.exception;

public class EquipistaNotFoundException extends ResourceNotFoundException {

    public EquipistaNotFoundException(Long id) {
        super("Equipista", "id", id);
    }

    public EquipistaNotFoundException(String message) {
        super(message);
    }
}

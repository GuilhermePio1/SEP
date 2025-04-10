package com.paroquia_santo_afonso.sep.SEP.common.exception;

public class EncontroNotFoundException extends ResourceNotFoundException {

  public EncontroNotFoundException(Long id) {
    super("Encontro", "id", id);
  }

  public EncontroNotFoundException(String message) {
    super(message);
  }
}

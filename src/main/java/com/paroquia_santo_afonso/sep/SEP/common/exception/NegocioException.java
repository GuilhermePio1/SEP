package com.paroquia_santo_afonso.sep.SEP.common.exception;

public class NegocioException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public NegocioException(String mensagem) {
		super(mensagem);
	}
	
	public NegocioException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}

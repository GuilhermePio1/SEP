package com.paroquia_santo_afonso.sep.SEP.common.exception;

public class EntidadeNaoEncontradaException extends NegocioException {
	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
}

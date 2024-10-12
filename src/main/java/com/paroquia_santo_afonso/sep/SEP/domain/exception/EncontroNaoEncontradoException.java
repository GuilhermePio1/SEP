package com.paroquia_santo_afonso.sep.SEP.domain.exception;

public class EncontroNaoEncontradoException extends EntidadeNaoEncontradaException {
	private static final long serialVersionUID = 1L;

	public EncontroNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public EncontroNaoEncontradoException(Long encontroId) {
		this(String.format("Não existe um cadastro de encontro com o código %d", encontroId));
	}
}

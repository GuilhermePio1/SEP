package com.paroquia_santo_afonso.sep.SEP.api.dto.builder;

import java.util.Base64;

import com.paroquia_santo_afonso.sep.SEP.api.dto.ListarPastaDTO;
import com.paroquia_santo_afonso.sep.SEP.api.dto.SalvarPastaDTO;
import com.paroquia_santo_afonso.sep.SEP.domain.model.Pasta;

public class PastaBuilder {
	public static ListarPastaDTO toListarPastaDTO(Pasta pasta) {
		return ListarPastaDTO.builder()
				.id(pasta.getId())
				.equipe(pasta.getEquipe())
				.arquivoBase64(byteToBase64(pasta.getArquivo()))
				.encontroId(pasta.getEncontro().getId())
				.build();
	}
	
	public static Pasta toPasta(SalvarPastaDTO salvarPastaDTO) {
		return Pasta.builder()
				.equipe(salvarPastaDTO.getEquipe())
				.arquivo(base64ToByte(salvarPastaDTO.getArquivoBase64()))
				.build();
	}
	
	private static String byteToBase64(byte[] arquivo) {
		return Base64.getEncoder().encodeToString(arquivo);
	}
	
	private static byte[] base64ToByte(String arquivo) {
		return Base64.getDecoder().decode(arquivo);
	}
}

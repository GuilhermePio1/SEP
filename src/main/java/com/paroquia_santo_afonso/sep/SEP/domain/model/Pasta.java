package com.paroquia_santo_afonso.sep.SEP.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "pastas")
@Data
@Builder
public class Pasta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeArquivo;

    private String contentType;

    @Lob
    private byte[] arquivo;

    public Pasta(Long id, String nomeArquivo, String contentType, byte[] arquivo) {
        this.id = id;
        this.nomeArquivo = nomeArquivo;
        this.contentType = contentType;
        this.arquivo = arquivo;
    }

    public Pasta(String nomeArquivo, String contentType, byte[] arquivo) {
        this.nomeArquivo = nomeArquivo;
        this.contentType = contentType;
        this.arquivo = arquivo;
    }

    public Pasta() { }
}

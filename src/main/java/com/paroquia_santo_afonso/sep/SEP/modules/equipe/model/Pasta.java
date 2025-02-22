package com.paroquia_santo_afonso.sep.SEP.modules.equipe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pastas")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pasta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeArquivo;

    private String contentType;

    @Lob
    private byte[] arquivo;

    public Pasta(String nomeArquivo, String contentType, byte[] arquivo) {
        this.nomeArquivo = nomeArquivo;
        this.contentType = contentType;
        this.arquivo = arquivo;
    }

}

package com.paroquia_santo_afonso.sep.SEP.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "pastas")
@Data
public class Pasta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "A equipe é obrigatório")
	private String equipe;
	
	@Lob
	private byte[] arquivo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "encontro_id")
	private Encontro encontro;
}
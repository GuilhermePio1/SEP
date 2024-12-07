package com.paroquia_santo_afonso.sep.SEP.domain.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "encontros")
@Data
@EqualsAndHashCode(exclude = "pastas")
@ToString(exclude = "pastas")
@Builder
public class Encontro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	
	@OneToMany(mappedBy = "encontro", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pasta> pastas;

	public Encontro(Long id, @NotBlank(message = "O nome é obrigatório") String nome, List<Pasta> pastas) {
		super();
		this.id = id;
		this.nome = nome;
		this.pastas = pastas;
	}
	
	public Encontro() { }
	
	public Encontro(Long id) {
		this.id = id;
	}
}

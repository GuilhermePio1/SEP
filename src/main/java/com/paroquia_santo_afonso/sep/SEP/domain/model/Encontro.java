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
import lombok.*;

@Entity
@Table(name = "encontros")
@Data
@EqualsAndHashCode(exclude = {"equipes"})
@ToString(exclude = {"equipes"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Encontro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	
	@OneToMany(mappedBy = "encontro", cascade = CascadeType.REMOVE)
	private List<Equipe> equipes;
	
	public Encontro(Long id) {
		this.id = id;
	}
}

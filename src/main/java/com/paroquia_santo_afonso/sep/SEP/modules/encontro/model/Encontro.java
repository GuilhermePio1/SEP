package com.paroquia_santo_afonso.sep.SEP.modules.encontro.model;

import java.util.List;

import com.paroquia_santo_afonso.sep.SEP.modules.equipe.model.Equipe;
import jakarta.persistence.*;
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

	@Column(nullable = false)
	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	
	@OneToMany(mappedBy = "encontro", cascade = CascadeType.REMOVE)
	private List<Equipe> equipes;
	
	public Encontro(Long id) {
		this.id = id;
	}
}

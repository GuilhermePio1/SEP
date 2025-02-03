package com.paroquia_santo_afonso.sep.SEP.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "equipes")
@Data
@EqualsAndHashCode(exclude = {"encontro", "pasta"})
@ToString(exclude = {"encontro", "pasta"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Equipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O nome da equipe é obrigatório")
	private String nome;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "encontro_id")
	private Encontro encontro;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pasta_id", referencedColumnName = "id")
	private Pasta pasta;

}

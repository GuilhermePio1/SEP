package com.paroquia_santo_afonso.sep.SEP.modules.equipe.model;

import com.paroquia_santo_afonso.sep.SEP.common.base.model.FileBaseEntity;
import com.paroquia_santo_afonso.sep.SEP.modules.encontro.model.Encontro;
import com.paroquia_santo_afonso.sep.SEP.modules.equipe.dto.EquipeResponseDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SqlResultSetMapping(
        name = "EquipeResponseDTOMapping",
        classes = @ConstructorResult(
                targetClass = EquipeResponseDTO.class,
                columns = {
                        @ColumnResult(name = "id"),
                        @ColumnResult(name = "nome"),
                }
        )
)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "equipes")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Equipe extends FileBaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotBlank(message = "O nome da equipe é obrigatório")
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "encontro_id", nullable = false)
	private Encontro encontro;

	public Equipe(Long id) {
		setId(id);
	}

}

package com.paroquia_santo_afonso.sep.SEP.modules.pastoral.model;

import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.Equipista;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "pastorais")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "equipistas")
@ToString(exclude = {"equipistas"})
public class Pastoral {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Column(nullable = false)
    private String nome;
    
    @ManyToMany(mappedBy = "pastorais", fetch = FetchType.LAZY)
    private List<Equipista> equipistas;

    public Pastoral(Long id) {
        this.id = id;
    }
}

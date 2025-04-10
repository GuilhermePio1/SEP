package com.paroquia_santo_afonso.sep.SEP.modules.equipista.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.EstadoCivil;
import com.paroquia_santo_afonso.sep.SEP.modules.equipista.model.Sacramento;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipistaRequestDTO {
    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NotNull(message = "A data de nascimento é obrigatória")
    @Past(message = "A data de nascimento deve estar no passado")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private EnderecoDTO endereco;

    @NotBlank(message = "O número de telefone é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O número de telefone deve conter 11 dígitos")
    private String numeroTelefone;

    private AreaAtuacaoDTO areaAtuacao;

    @NotBlank(message = "O estado civil é obrigatório")    
    private String estadoCivil;

    private String filhos;

    private List<Long> idPastorais;

    private String sacramento;

    private List<ParticipacaoEncontroRequestDTO> participacoesEncontro;

    public EstadoCivil getEstadoCivilEnum() {
        return estadoCivil != null ? EstadoCivil.valueOf(estadoCivil) : null;
    }

    public Sacramento getSacramentoEnum() {
        return sacramento != null ? Sacramento.valueOf(sacramento) : null;
    }
}

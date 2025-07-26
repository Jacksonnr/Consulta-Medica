package projeto_tomorrow.demo.DTO.PacientesDTO;

import jakarta.validation.constraints.*;
import projeto_tomorrow.demo.Entity.enums.Convenio;

import java.time.LocalDate;

public record PacienteDTORequest(
        @NotBlank(message = "O campo 'nome' é obrigatório.")
        @Size(min = 3, max = 50, message = "O campo 'nome' deve ter entre 3 e 50 caracteres.")
        String nome,

        @Email(message = "O email precisa ser válido")
        String email,

        @Min(value = 8, message = "O campo 'senha' deve ter no mínimo 8 caracteres.")
        String senha,

        @Past(message = "A data de nascimento deve ser anterior à data atual.")
        LocalDate dataNascimento,

        @NotBlank(message = "O campo 'CPF' é obrigatório")
        @Min(value = 11, message = "CPF inválido")
        @Max(value = 14, message = "CPF inválido")
        String cpf,

        @NotBlank(message = "O campo 'telefone' é obrigatório.")
        String telefone,

        Convenio convenio,
        String numeroCarteirinha
) {
}

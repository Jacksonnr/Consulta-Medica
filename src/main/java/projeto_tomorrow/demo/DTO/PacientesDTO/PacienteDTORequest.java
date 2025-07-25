package projeto_tomorrow.demo.DTO.PacientesDTO;

import projeto_tomorrow.demo.Entity.enums.Convenio;

import java.time.LocalDate;

public record PacienteDTORequest(
        String nome,
        String email,
        String senha,
        LocalDate dataNascimento,
        String cpf,
        String telefone,
        Convenio convenio,
        String numeroCarteirinha
) {
}

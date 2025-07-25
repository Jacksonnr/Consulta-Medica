package projeto_tomorrow.demo.DTO.PacientesDTO;

import projeto_tomorrow.demo.Entity.enums.Convenio;

import java.time.LocalDate;

public record PacienteDTOResponse(
        String nome,
        String email,
        LocalDate dataNascimento,
        String cpf,
        String telefone,
        Convenio convenio,
        String numeroCarteirinha
) {
}

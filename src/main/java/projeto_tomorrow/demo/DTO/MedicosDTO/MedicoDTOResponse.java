package projeto_tomorrow.demo.DTO.MedicosDTO;

import projeto_tomorrow.demo.Entity.enums.EspecialidadeMedica;

public record MedicoDTOResponse(
        String nome,
        String email,
        EspecialidadeMedica especialidade
) {
}

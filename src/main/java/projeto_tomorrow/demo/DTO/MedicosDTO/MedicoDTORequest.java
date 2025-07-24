package projeto_tomorrow.demo.DTO.MedicosDTO;

import projeto_tomorrow.demo.Entity.enums.EspecialidadeMedica;

public record MedicoDTORequest(
        String nome,
        String email,
        String senha,
        EspecialidadeMedica especialidade
) {
}

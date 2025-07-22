package projeto_tomorrow.demo.DTO.MedicosDTO;

import projeto_tomorrow.demo.Entity.enums.UserRole;

public record MedicoDTOResponse(
        String nome,
        String email,
        String especialidade,
        UserRole role
) {
}

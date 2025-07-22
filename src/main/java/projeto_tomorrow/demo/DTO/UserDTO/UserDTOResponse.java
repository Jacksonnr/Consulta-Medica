package projeto_tomorrow.demo.DTO.UserDTO;

import projeto_tomorrow.demo.Entity.enums.UserRole;

public record UserDTOResponse(
        Long id,
        String nome,
        String email,
        UserRole role) {


}

package projeto_tomorrow.demo.DTO.UserDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDTORequest(

    @NotBlank(message = "O campo 'nome' é obrigatório.")
    @Size(min = 3, max = 50, message = "O campo 'nome' deve ter entre 3 e 50 caracteres.")
    String nome,

    @Email(message = "O email precisa ser válido")
    String email,

    @Min(value = 8, message = "O campo 'senha' deve ter no mínimo 8 caracteres.")
    String senha
) {
}

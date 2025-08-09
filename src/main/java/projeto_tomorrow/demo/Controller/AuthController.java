package projeto_tomorrow.demo.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import projeto_tomorrow.demo.DTO.LoginDTO.LoginRequestDTO;
import projeto_tomorrow.demo.DTO.LoginDTO.LoginResponseDTO;
import projeto_tomorrow.demo.Entity.User;
import projeto_tomorrow.demo.Exceptions.NotFoundException;
import projeto_tomorrow.demo.Exceptions.PasswordInvalidException;
import projeto_tomorrow.demo.Repository.UserRepository;
import projeto_tomorrow.demo.security.TokenService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        if (!passwordEncoder.matches(dto.password(), user.getSenha())) {
            throw new PasswordInvalidException("Senha inválida");
        }

        String token = tokenService.generateToken(user);
        return ResponseEntity.ok(new LoginResponseDTO(user.getEmail(), token));
    }
}

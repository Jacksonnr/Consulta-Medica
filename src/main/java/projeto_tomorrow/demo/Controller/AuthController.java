package projeto_tomorrow.demo.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto_tomorrow.demo.DTO.LoginDTO.LoginRequestDTO;
import projeto_tomorrow.demo.DTO.LoginDTO.LoginResponseDTO;
import projeto_tomorrow.demo.DTO.RegisterDTO.RegisterRequestDTO;
import projeto_tomorrow.demo.Entity.User;
import projeto_tomorrow.demo.Exceptions.NotFoundException;
import projeto_tomorrow.demo.Repository.UserRepository;
import projeto_tomorrow.demo.security.TokenService;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginRequestDTO dto){
        User user = this.userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));

        if(passwordEncoder.matches(dto.password(), user.getSenha())){
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponseDTO(user.getEmail(), token));
        }
        return ResponseEntity.status(401).body("Senha inválida");
    }

    @PostMapping("registro")
    public ResponseEntity<?> register (@RequestBody RegisterRequestDTO dto){

        Optional<User> user = this.userRepository.findByEmail(dto.email());

        if (user.isEmpty()){
            User newUser = new User();
            newUser.setSenha(passwordEncoder.encode(dto.password()));
            newUser.setEmail(dto.email());
            newUser.setNome(dto.name());
            this.userRepository.save(newUser);

                String token = this.tokenService.generateToken(newUser);
                return ResponseEntity.ok(new LoginResponseDTO(newUser.getEmail(), token));

        }
        return ResponseEntity.badRequest().build();
    }
}

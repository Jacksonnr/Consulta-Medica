package projeto_tomorrow.demo.Service;

import org.springframework.stereotype.Service;
import projeto_tomorrow.demo.DTO.PacientesDTO.PacienteDTORequest;
import projeto_tomorrow.demo.DTO.PacientesDTO.PacienteDTOResponse;
import projeto_tomorrow.demo.Entity.PerfilPaciente;
import projeto_tomorrow.demo.Entity.User;
import projeto_tomorrow.demo.Entity.enums.UserRole;
import projeto_tomorrow.demo.Repository.PacienteRepository;
import projeto_tomorrow.demo.Repository.UserRepository;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final UserRepository userRepository;

    public PacienteService(PacienteRepository pacienteRepository, UserRepository userRepository) {
        this.pacienteRepository = pacienteRepository;
        this.userRepository = userRepository;
    }

    public PacienteDTOResponse criar(PacienteDTORequest dto){
        pacienteRepository.findByCpf(dto.cpf())
                .ifPresent(existingEmail -> {
                    throw new RuntimeException("CPF informado já cadastrado, recupere a senha!");
                });

        User usuario = new User();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setRole(UserRole.PACIENTE);

        User usuarioSalvo = userRepository.save(usuario);

        PerfilPaciente paciente = new PerfilPaciente(dto, usuarioSalvo);
        pacienteRepository.save(paciente);

        return new PacienteDTOResponse(dto.nome(), dto.email(), dto.dataNascimento(),
                dto.cpf(), dto.telefone(), dto.convenio(), dto.numeroCarteirinha());
    }


}

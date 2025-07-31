package projeto_tomorrow.demo.Service;

import org.springframework.stereotype.Service;
import projeto_tomorrow.demo.DTO.PacientesDTO.PacienteDTORequest;
import projeto_tomorrow.demo.DTO.PacientesDTO.PacienteDTOResponse;
import projeto_tomorrow.demo.Entity.PerfilPaciente;
import projeto_tomorrow.demo.Entity.User;
import projeto_tomorrow.demo.Entity.enums.UserRole;
import projeto_tomorrow.demo.Exceptions.CpfAlreadyExistsException;
import projeto_tomorrow.demo.Exceptions.NotFoundException;
import projeto_tomorrow.demo.Repository.ConsultaRepository;
import projeto_tomorrow.demo.Repository.PacienteRepository;
import projeto_tomorrow.demo.Repository.UserRepository;

import java.util.List;

@Service
public class PacienteService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final UserRepository userRepository;

    public PacienteService(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository, UserRepository userRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.userRepository = userRepository;
    }

    public PacienteDTOResponse criar(PacienteDTORequest dto){
        pacienteRepository.findByCpf(dto.cpf())
                .ifPresent(existingEmail -> {
                    throw new CpfAlreadyExistsException("CPF informado já cadastrado, recupere a senha!");
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

    public List<PacienteDTOResponse> listarTodos (){
        return pacienteRepository.findAll().stream().map(paciente -> new PacienteDTOResponse(
                paciente.getUsuario().getNome(),
                paciente.getUsuario().getEmail(),
                paciente.getDataNascimento(),
                paciente.getCpf(),
                paciente.getTelefone(),
                paciente.getConvenio(),
                paciente.getNumeroCarteirinha()
        )).toList();
    }

    public PacienteDTOResponse listarPorId(Long id){
         PerfilPaciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Paciente não encontrado com o id informado"));

         return new PacienteDTOResponse(paciente.getUsuario().getNome(),
                 paciente.getUsuario().getEmail(),
                 paciente.getDataNascimento(),
                 paciente.getCpf(),
                 paciente.getTelefone(),
                 paciente.getConvenio(),
                 paciente.getNumeroCarteirinha());
    }

    public List<PacienteDTOResponse> listarPacientesDoMedico(User medico) {
        List<PerfilPaciente> pacientes = consultaRepository.findPacientesByMedicoId(medico.getId());

        return pacientes.stream()
                .map(p -> new PacienteDTOResponse(
                        p.getUsuario().getNome(),
                        p.getUsuario().getEmail(),
                        p.getDataNascimento(),
                        p.getCpf(),
                        p.getTelefone(),
                        p.getConvenio(),
                        p.getNumeroCarteirinha()
                ))
                .toList();
    }

    public PacienteDTOResponse atualizar(Long id, PacienteDTORequest dto){
        PerfilPaciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Paciente não encontrado com o id informado"));

        paciente.getUsuario().setNome(dto.nome());
        paciente.setDataNascimento(dto.dataNascimento());
        paciente.setCpf(dto.cpf());
        paciente.setTelefone(dto.telefone());
        paciente.setConvenio(dto.convenio());
        paciente.setNumeroCarteirinha(dto.numeroCarteirinha());

        pacienteRepository.save(paciente);

        return new PacienteDTOResponse(
                paciente.getUsuario().getNome(),
                paciente.getUsuario().getEmail(),
                paciente.getDataNascimento(),
                paciente.getCpf(),
                paciente.getTelefone(),
                paciente.getConvenio(),
                paciente.getNumeroCarteirinha()
        );

    }

    public void deletar (Long id){
        PerfilPaciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Paciente não encontrado com o id informado"));

        pacienteRepository.delete(paciente);
    }


}

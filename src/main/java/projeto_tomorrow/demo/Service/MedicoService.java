package projeto_tomorrow.demo.Service;

import org.springframework.stereotype.Service;
import projeto_tomorrow.demo.DTO.MedicosDTO.MedicoDTORequest;
import projeto_tomorrow.demo.DTO.MedicosDTO.MedicoDTOResponse;
import projeto_tomorrow.demo.Entity.PerfilMedico;
import projeto_tomorrow.demo.Entity.User;
import projeto_tomorrow.demo.Entity.enums.EspecialidadeMedica;
import projeto_tomorrow.demo.Entity.enums.UserRole;
import projeto_tomorrow.demo.Exceptions.EmailAlreadyExistsException;
import projeto_tomorrow.demo.Exceptions.NotFoundException;
import projeto_tomorrow.demo.Repository.MedicoRepository;
import projeto_tomorrow.demo.Repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final UserRepository userRepository;

    public MedicoService(MedicoRepository medicoRepository, UserRepository userRepository) {
        this.medicoRepository = medicoRepository;
        this.userRepository = userRepository;
    }

    public MedicoDTOResponse criar(MedicoDTORequest dto) {
        medicoRepository.findByUsuarioEmail(dto.email())
                .ifPresent(existingEmail -> {
                    throw new EmailAlreadyExistsException("Email informado já cadastrado, recupere a senha ou insira outro email!");
                });

        User usuario = new User();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setSenha(dto.senha());
        usuario.setRole(UserRole.MEDICO);
        
        User usuarioSalvo = userRepository.save(usuario);

        // Criar o PerfilMedico com o User
        PerfilMedico perfilMedico = new PerfilMedico(dto, usuarioSalvo);
        medicoRepository.save(perfilMedico);

        return new MedicoDTOResponse(dto.nome(), dto.email(), dto.especialidade());
    }

    public List<MedicoDTOResponse> listarTodos(){
        return medicoRepository.findAll()
                .stream()
                .map(medico -> new MedicoDTOResponse(
                medico.getUsuario().getNome(),
                medico.getUsuario().getEmail(),
                medico.getEspecialidade()
                )).toList();
    }

    public List<MedicoDTOResponse> buscarPorEspecialidade(EspecialidadeMedica especialidade) {

        List<PerfilMedico> medicos = medicoRepository.findByEspecialidade(especialidade);

        if (medicos.isEmpty()) {
            throw new NotFoundException("Nenhum médico encontrado para a especialidade: " + especialidade);
        }

        return medicos.stream()
                .map(medico -> new MedicoDTOResponse(
                        medico.getUsuario().getNome(),
                        medico.getUsuario().getEmail(),
                        medico.getEspecialidade()
                )).collect(Collectors.toList());
    }

    public List<MedicoDTOResponse> buscarPorNomeParcial(String nome) {
        return medicoRepository.findByUsuarioNomeContainingIgnoreCase(nome)
                .stream()
                .map(medico -> new MedicoDTOResponse(
                        medico.getUsuario().getNome(),
                        medico.getUsuario().getEmail(),
                        medico.getEspecialidade()
                )).toList();
    }

    public MedicoDTOResponse atualizar(Long id, MedicoDTORequest dto) {
        PerfilMedico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Médico não encontrado"));

        medico.getUsuario().setNome(dto.nome());
        medico.getUsuario().setEmail(dto.email());
        medico.setEspecialidade(dto.especialidade());

        medicoRepository.save(medico);

        return new MedicoDTOResponse(
                medico.getUsuario().getNome(),
                medico.getUsuario().getEmail(),
                medico.getEspecialidade()
        );
    }

    public void deletar(Long id) {
        PerfilMedico medico = medicoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Médico não encontrado"));

        medicoRepository.delete(medico);
    }
}
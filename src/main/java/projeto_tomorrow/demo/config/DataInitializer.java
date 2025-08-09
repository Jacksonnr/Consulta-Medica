package projeto_tomorrow.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import projeto_tomorrow.demo.Entity.Consulta;
import projeto_tomorrow.demo.Entity.PerfilMedico;
import projeto_tomorrow.demo.Entity.PerfilPaciente;
import projeto_tomorrow.demo.Entity.User;
import projeto_tomorrow.demo.Entity.enums.Convenio;
import projeto_tomorrow.demo.Entity.enums.EspecialidadeMedica;
import projeto_tomorrow.demo.Entity.enums.StatusConsulta;
import projeto_tomorrow.demo.Entity.enums.UserRole;
import projeto_tomorrow.demo.Repository.ConsultaRepository;
import projeto_tomorrow.demo.Repository.MedicoRepository;
import projeto_tomorrow.demo.Repository.PacienteRepository;
import projeto_tomorrow.demo.Repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final ConsultaRepository consultaRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        // Criar usuários médicos
        User user1 = new User();
        user1.setNome("Dr. João Silva");
        user1.setEmail("joao.silva@hospital.com");
        user1.setSenha(passwordEncoder.encode("123456"));
        user1.setRole(UserRole.MEDICO);
        User savedUser1 = userRepository.save(user1);

        User user2 = new User();
        user2.setNome("Dra. Maria Santos");
        user2.setEmail("maria.santos@hospital.com");
        user2.setSenha(passwordEncoder.encode("123456"));
        user2.setRole(UserRole.MEDICO);
        User savedUser2 = userRepository.save(user2);

        User user3 = new User();
        user3.setNome("Dr. Pedro Costa");
        user3.setEmail("pedro.costa@hospital.com");
        user3.setSenha(passwordEncoder.encode("123456"));
        user3.setRole(UserRole.MEDICO);
        User savedUser3 = userRepository.save(user3);

        // Criar usuário paciente
        User paciente = new User();
        paciente.setNome("Ana Oliveira");
        paciente.setEmail("ana.oliveira@email.com");
        paciente.setSenha(passwordEncoder.encode("123456"));
        paciente.setRole(UserRole.PACIENTE);
        userRepository.save(paciente);

        // Criar perfis médicos
        PerfilMedico medico1 = new PerfilMedico();
        medico1.setEspecialidade(EspecialidadeMedica.CARDIOLOGIA);
        medico1.setUsuario(savedUser1);
        medicoRepository.save(medico1);

        PerfilMedico medico2 = new PerfilMedico();
        medico2.setEspecialidade(EspecialidadeMedica.DERMATOLOGIA);
        medico2.setUsuario(savedUser2);
        medicoRepository.save(medico2);

        PerfilMedico medico3 = new PerfilMedico();
        medico3.setEspecialidade(EspecialidadeMedica.PEDIATRIA);
        medico3.setUsuario(savedUser3);
        medicoRepository.save(medico3);

        // Criar perfis paciente
        PerfilPaciente paciente1 = new PerfilPaciente();
        paciente1.setDataNascimento(LocalDate.of(1990, 5, 15));
        paciente1.setCpf("123.456.789-00");
        paciente1.setTelefone("71912345678");
        paciente1.setConvenio(Convenio.UNIMED);
        paciente1.setNumeroCarteirinha("1010.432-24");
        paciente1.setUsuario(paciente);
        pacienteRepository.save(paciente1);

        // Criar consultas
        Consulta consulta1 = new Consulta();
        consulta1.setMedico(medico1);
        consulta1.setPaciente(paciente1);
        consulta1.setDataHora(LocalDateTime.of(2025,10,12,15,30));
        consulta1.setObservacao("Sem observações");
        consulta1.setStatusConsulta(StatusConsulta.AGENDADA);
        consultaRepository.save(consulta1);

        // Criar Admin
        User userAdmin = new User();
        userAdmin.setNome("Admin");
        userAdmin.setEmail("admin@admin");
        userAdmin.setSenha(passwordEncoder.encode("admin"));
        userAdmin.setRole(UserRole.ADMIN);
        User saveAdmin = userRepository.save(userAdmin);


        System.out.println("Dados iniciais carregados com sucesso!");
        System.out.println("teste");
    }
}
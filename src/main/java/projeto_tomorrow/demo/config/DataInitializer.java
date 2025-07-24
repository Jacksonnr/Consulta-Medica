package projeto_tomorrow.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import projeto_tomorrow.demo.Entity.PerfilMedico;
import projeto_tomorrow.demo.Entity.User;
import projeto_tomorrow.demo.Entity.enums.EspecialidadeMedica;
import projeto_tomorrow.demo.Entity.enums.UserRole;
import projeto_tomorrow.demo.Repository.MedicoRepository;
import projeto_tomorrow.demo.Repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final MedicoRepository medicoRepository;

    public DataInitializer(UserRepository userRepository, MedicoRepository medicoRepository) {
        this.userRepository = userRepository;
        this.medicoRepository = medicoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Criar usuários médicos
        User user1 = new User();
        user1.setNome("Dr. João Silva");
        user1.setEmail("joao.silva@hospital.com");
        user1.setSenha("123456");
        user1.setRole(UserRole.MEDICO);
        User savedUser1 = userRepository.save(user1);

        User user2 = new User();
        user2.setNome("Dra. Maria Santos");
        user2.setEmail("maria.santos@hospital.com");
        user2.setSenha("123456");
        user2.setRole(UserRole.MEDICO);
        User savedUser2 = userRepository.save(user2);

        User user3 = new User();
        user3.setNome("Dr. Pedro Costa");
        user3.setEmail("pedro.costa@hospital.com");
        user3.setSenha("123456");
        user3.setRole(UserRole.MEDICO);
        User savedUser3 = userRepository.save(user3);

        // Criar usuário paciente
        User paciente = new User();
        paciente.setNome("Ana Oliveira");
        paciente.setEmail("ana.oliveira@email.com");
        paciente.setSenha("123456");
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

        System.out.println("Dados iniciais carregados com sucesso!");
    }
}
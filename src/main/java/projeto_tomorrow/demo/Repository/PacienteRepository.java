package projeto_tomorrow.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto_tomorrow.demo.Entity.PerfilPaciente;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<PerfilPaciente, Long> {

    Optional<PerfilPaciente> findByCpf(String cpf);


}

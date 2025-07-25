package projeto_tomorrow.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto_tomorrow.demo.Entity.PerfilMedico;
import projeto_tomorrow.demo.Entity.enums.EspecialidadeMedica;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<PerfilMedico, Long> {

    Optional<PerfilMedico> findByUsuarioEmail(String email);

    List<PerfilMedico> findByEspecialidade(EspecialidadeMedica especialidade);

    List<PerfilMedico> findByUsuarioNomeContainingIgnoreCase(String nome);

}

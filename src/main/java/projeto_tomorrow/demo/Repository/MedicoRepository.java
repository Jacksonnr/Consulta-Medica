package projeto_tomorrow.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto_tomorrow.demo.Entity.Perfis_Medicos;

public interface MedicoRepository extends JpaRepository<Perfis_Medicos, Long> {
}

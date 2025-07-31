package projeto_tomorrow.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import projeto_tomorrow.demo.Entity.Consulta;
import projeto_tomorrow.demo.Entity.PerfilPaciente;

import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    @Query("SELECT c FROM Consulta c " +
            "JOIN FETCH c.medico m " +
            "JOIN FETCH m.usuario um " +
            "JOIN FETCH c.paciente p " +
            "JOIN FETCH p.usuario up " +
            "ORDER BY c.dataHora")
    List<Consulta> findAllWithMedicoAndPacienteNames();


    @Query("SELECT c.paciente FROM Consulta c " +
            "WHERE c.medico.id = :medicoId")
    List<PerfilPaciente> findPacientesByMedicoId(@Param("medicoId") Long medicoId);

}


package projeto_tomorrow.demo.DTO.ConsultaDTO;

import projeto_tomorrow.demo.Entity.PerfilMedico;
import projeto_tomorrow.demo.Entity.PerfilPaciente;
import projeto_tomorrow.demo.Entity.enums.StatusConsulta;

import java.time.LocalDateTime;

public record ConsultaDTORequest (
        PerfilMedico medico,
        PerfilPaciente paciente,
        LocalDateTime dataHora,
        String observacao,
        StatusConsulta statusConsulta
){
}

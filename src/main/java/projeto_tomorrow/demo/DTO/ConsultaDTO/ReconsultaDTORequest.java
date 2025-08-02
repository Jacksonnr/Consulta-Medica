package projeto_tomorrow.demo.DTO.ConsultaDTO;

import projeto_tomorrow.demo.Entity.PerfilMedico;
import projeto_tomorrow.demo.Entity.enums.StatusConsulta;

import java.time.LocalDateTime;

public record ReconsultaDTORequest(
        Long idConsulta,
        LocalDateTime dataHora,
        String observacao,
        PerfilMedico nomeMedico,
        StatusConsulta status
) {
}

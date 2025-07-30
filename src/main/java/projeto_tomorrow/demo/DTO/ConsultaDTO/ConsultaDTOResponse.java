package projeto_tomorrow.demo.DTO.ConsultaDTO;

import projeto_tomorrow.demo.Entity.enums.StatusConsulta;

import java.time.LocalDateTime;

public record ConsultaDTOResponse(
        Long medicoId,
        Long pacienteId,
        LocalDateTime dataHora,
        String observacao,
        StatusConsulta statusConsulta
) {
}

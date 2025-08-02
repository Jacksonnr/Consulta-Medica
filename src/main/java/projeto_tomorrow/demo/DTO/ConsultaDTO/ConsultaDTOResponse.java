package projeto_tomorrow.demo.DTO.ConsultaDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import projeto_tomorrow.demo.Entity.PerfilMedico;
import projeto_tomorrow.demo.Entity.PerfilPaciente;
import projeto_tomorrow.demo.Entity.enums.StatusConsulta;

import java.time.LocalDateTime;

public record ConsultaDTOResponse(
        String medicoNome,
        String pacienteNome,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "UTC")
        LocalDateTime dataHora,
        String observacao,
        StatusConsulta statusConsulta
) {
}

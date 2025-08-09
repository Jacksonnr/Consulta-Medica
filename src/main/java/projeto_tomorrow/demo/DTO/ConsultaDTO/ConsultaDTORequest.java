package projeto_tomorrow.demo.DTO.ConsultaDTO;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import projeto_tomorrow.demo.Entity.enums.StatusConsulta;

import java.time.LocalDateTime;

public record ConsultaDTORequest (
        @NotNull(message = "O Id do médico precisa ser inserido")
        Long medicoId,

        @NotNull(message = "O Id do paciente precisa ser inserido")
        Long pacienteId,

        @Future(message = "A data/hora da consulta deve ser no futuro")
        @NotNull(message = "A data/hora não pode ser nulo")
        LocalDateTime dataHora,

        String observacao,
        StatusConsulta statusConsulta
){
}

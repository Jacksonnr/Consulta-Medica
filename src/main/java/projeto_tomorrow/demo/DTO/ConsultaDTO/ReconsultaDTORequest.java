package projeto_tomorrow.demo.DTO.ConsultaDTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import projeto_tomorrow.demo.Entity.PerfilMedico;
import projeto_tomorrow.demo.Entity.enums.StatusConsulta;

import java.time.LocalDateTime;

public record ReconsultaDTORequest(
        Long idConsulta,

        @Future(message = "A data/hora da consulta deve ser no futuro")
        @NotNull(message = "A data/hora não pode ser nulo")

        LocalDateTime dataHora,

        String observacao,

        PerfilMedico nomeMedico,
        StatusConsulta status
) {
}

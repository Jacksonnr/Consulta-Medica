package projeto_tomorrow.demo.Entity.enums;

import lombok.Getter;

@Getter
public enum StatusConsulta {

    AGENDADA("Agendada"),
    REALIZADA("Realizada"),
    CONCLUIDA("Concluída"),
    CANCELADA("Cancelada"),
    RECONSULTA("Reconsulta");

    private final String descricao;

    StatusConsulta(String descricao) {
        this.descricao = descricao;
    }
}

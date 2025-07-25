package projeto_tomorrow.demo.Entity.enums;

public enum Convenio {
    UNIMED("Unimed"),
    BRADESCO_SAUDE("Bradesco"),
    AMIL("Amil"),
    SULAMERICA("Sulamerica"),
    HAPVIDA("Hapvida"),
    PARTICULAR("Particular");

    private final String descricao;

    Convenio(String descricao) {
        this.descricao = descricao;
    }
}
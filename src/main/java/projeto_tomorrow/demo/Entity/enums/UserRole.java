package projeto_tomorrow.demo.Entity.enums;

public enum UserRole {

    ADMIN("admin"),
    PACIENTE("paciente"),
    MEDICO("medico");

    private String role;

    UserRole(String role) {
        this.role = role;
    }
}

package projeto_tomorrow.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projeto_tomorrow.demo.DTO.PacientesDTO.PacienteDTORequest;
import projeto_tomorrow.demo.Entity.enums.Convenio;

import java.time.LocalDate;

@Entity
@Table(name = "TB_PERFIL_PACIENTE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilPaciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataNascimento;
    
    private String cpf;
    
    private String telefone;
    
    @Enumerated(EnumType.STRING)
    private Convenio convenio;
    
    private String numeroCarteirinha;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User usuario;


    public PerfilPaciente(PacienteDTORequest dto, User usuarioSalvo) {
        this.dataNascimento = dto.dataNascimento();
        this.cpf = dto.cpf();
        this.telefone = dto.telefone();
        this.convenio = dto.convenio();
        this.numeroCarteirinha = dto.numeroCarteirinha();
        this.usuario = usuarioSalvo;
    }
}

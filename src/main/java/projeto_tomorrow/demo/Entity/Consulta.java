package projeto_tomorrow.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projeto_tomorrow.demo.Entity.enums.StatusConsulta;

import java.time.LocalDateTime;

@Entity
@Table (name = "TB_CONSULTA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id", nullable = false)
    private PerfilMedico medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private PerfilPaciente paciente;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    private String observacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusConsulta statusConsulta;

    @ManyToOne
    private Consulta consultaOriginal;

}

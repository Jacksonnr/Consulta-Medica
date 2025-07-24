package projeto_tomorrow.demo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projeto_tomorrow.demo.DTO.MedicosDTO.MedicoDTORequest;
import projeto_tomorrow.demo.Entity.enums.EspecialidadeMedica;

@Entity
@Table(name = "TB_PERFIL_MEDICO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilMedico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EspecialidadeMedica especialidade;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User usuario;

    public PerfilMedico(MedicoDTORequest medico, User usuario) {
        this.especialidade = medico.especialidade();
        this.usuario = usuario;
    }
}
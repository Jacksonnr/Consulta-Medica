package projeto_tomorrow.demo.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private Integer age;
    private String name;
    private String email;


}

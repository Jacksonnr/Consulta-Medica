package projeto_tomorrow.demo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto_tomorrow.demo.DTO.PacientesDTO.PacienteDTORequest;
import projeto_tomorrow.demo.DTO.PacientesDTO.PacienteDTOResponse;
import projeto_tomorrow.demo.Service.PacienteService;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<PacienteDTOResponse> criar(@RequestBody PacienteDTORequest dto){
        PacienteDTOResponse paciente = pacienteService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
    }
}

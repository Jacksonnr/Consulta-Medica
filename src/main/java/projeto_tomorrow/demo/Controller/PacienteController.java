package projeto_tomorrow.demo.Controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto_tomorrow.demo.DTO.PacientesDTO.PacienteDTORequest;
import projeto_tomorrow.demo.DTO.PacientesDTO.PacienteDTOResponse;
import projeto_tomorrow.demo.Entity.User;
import projeto_tomorrow.demo.Service.PacienteService;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<PacienteDTOResponse> criar(@Valid @RequestBody PacienteDTORequest dto){
        PacienteDTOResponse paciente = pacienteService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
    }

    @GetMapping
    public ResponseEntity<List<PacienteDTOResponse>> listarTodos(){
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    @GetMapping("/minhas-consultas")
    public ResponseEntity<List<PacienteDTOResponse>> listarParaMedicos (User medico){
        return ResponseEntity.ok(pacienteService.listarPacientesDoMedico(medico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTOResponse> listarPorId(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.listarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTOResponse> atualizar(@PathVariable Long id, @RequestBody @Valid PacienteDTORequest dto){
        return ResponseEntity.ok(pacienteService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PacienteDTOResponse> deletar(@PathVariable Long id){
        pacienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

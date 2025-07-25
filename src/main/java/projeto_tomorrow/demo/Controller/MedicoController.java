package projeto_tomorrow.demo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto_tomorrow.demo.DTO.MedicosDTO.MedicoDTORequest;
import projeto_tomorrow.demo.DTO.MedicosDTO.MedicoDTOResponse;
import projeto_tomorrow.demo.Entity.enums.EspecialidadeMedica;
import projeto_tomorrow.demo.Service.MedicoService;

import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @PostMapping
    public ResponseEntity<MedicoDTOResponse> criar(@RequestBody MedicoDTORequest dto){
        MedicoDTOResponse medicoDTOResponse = medicoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoDTOResponse);
    }

    @GetMapping
    public ResponseEntity<List<MedicoDTOResponse>> listarTodos(){
        return ResponseEntity.ok(medicoService.listarTodos());
    }

    @GetMapping("/especialidade")
    public ResponseEntity<List<MedicoDTOResponse>> listarPorEspecialidade(@RequestParam("especialidade") EspecialidadeMedica especialidade){
        return ResponseEntity.ok(medicoService.buscarPorEspecialidade(especialidade));
    }

    @GetMapping("/nome")
    public ResponseEntity<List<MedicoDTOResponse>> listarPorNome(@RequestParam("nome") String nome){
        return ResponseEntity.ok(medicoService.buscarPorNomeParcial(nome));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTOResponse> atualizar(@PathVariable("id") Long id, @RequestBody MedicoDTORequest dto){
        return ResponseEntity.ok(medicoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        medicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

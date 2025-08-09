package projeto_tomorrow.demo.Controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto_tomorrow.demo.DTO.ConsultaDTO.ConsultaDTORequest;
import projeto_tomorrow.demo.DTO.ConsultaDTO.ConsultaDTOResponse;
import projeto_tomorrow.demo.DTO.ConsultaDTO.ReconsultaDTORequest;
import projeto_tomorrow.demo.Entity.enums.StatusConsulta;
import projeto_tomorrow.demo.Service.ConsultaService;

import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    private final ConsultaService consultaService;

    public  ConsultaController (ConsultaService consultaService) { this.consultaService = consultaService;}

    @PostMapping
    public ResponseEntity<ConsultaDTOResponse> createConsult (@RequestBody ConsultaDTORequest consultaDTORequest){
        ConsultaDTOResponse consultaDTOResponse = consultaService.criarConsulta(consultaDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaDTOResponse);
    }

    @GetMapping
    public ResponseEntity<List<ConsultaDTOResponse>> listAllConsults(){
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTOResponse> findById (@PathVariable Long id){
        return ResponseEntity.ok(consultaService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaDTOResponse> updateConsult (@PathVariable("id") Long id, @RequestBody ConsultaDTORequest consultaDTORequest){
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.update(id, consultaDTORequest));
    }

    @PutMapping("/confirmarConsulta/{id}")
    public ResponseEntity<ConsultaDTOResponse> confirmarConsulta (@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.consultaRealizada(id));
    }

    @PutMapping("/concluirConsulta/{id}")
    public ResponseEntity<ConsultaDTOResponse> concluirConsulta (@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.concluirConsulta(id));
    }

    @PutMapping("/cancelarConsulta/{id}")
    public ResponseEntity<ConsultaDTOResponse> cancelarConsulta (@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.cancelarConsulta(id));
    }

    @PostMapping("/reconsulta/{id}")
    public ResponseEntity<ConsultaDTOResponse> marcarReconsulta (@PathVariable("id") Long id,
                                                                 @RequestBody ReconsultaDTORequest dto){
        ConsultaDTOResponse consultaDTOResponse = consultaService.reconsulta(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaDTOResponse);
    }

    @GetMapping("/status")
    public ResponseEntity<List<ConsultaDTOResponse>> findByStatus (@RequestParam("status") StatusConsulta status){
        return ResponseEntity.status(HttpStatus.OK).body(consultaService.findByStatus(status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsult (@PathVariable("id") Long id){
        consultaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

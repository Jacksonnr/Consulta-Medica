package projeto_tomorrow.demo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto_tomorrow.demo.DTO.UserDTO.UserDTORequest;
import projeto_tomorrow.demo.DTO.UserDTO.UserDTOResponse;
import projeto_tomorrow.demo.Service.UserService;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTOResponse> criarUsuario(@RequestBody UserDTORequest userDTORequest){
        UserDTOResponse userDTOResponse = userService.criar(userDTORequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTOResponse);
    }

    @GetMapping
    public ResponseEntity<List<UserDTOResponse>> listarUsuarios (){
        return ResponseEntity.ok(userService.listarTodos());
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDTOResponse> listarUsuario(@PathVariable long id){
        UserDTOResponse userDTOResponse = userService.buscarPorId(id);
        return ResponseEntity.ok(userDTOResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable long id){
        userService.deletar(id);
        return ResponseEntity.noContent().build();
    }

}

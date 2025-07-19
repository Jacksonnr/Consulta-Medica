package projeto_tomorrow.demo.Service;

import org.springframework.stereotype.Service;
import projeto_tomorrow.demo.Entity.DTO.UserDTORequest;
import projeto_tomorrow.demo.Entity.DTO.UserDTOResponse;
import projeto_tomorrow.demo.Entity.User;
import projeto_tomorrow.demo.UserRepository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserDTOResponse criar(UserDTORequest dtoRequest){

        User user = new User();
        user.setName(dtoRequest.name());
        user.setEmail(dtoRequest.email());
        user.setAge(dtoRequest.age());

        repository.save(user);

        return new UserDTOResponse(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAge());
    }

    public List<UserDTOResponse> listarTodos(){
        return repository.findAll().stream().map(user -> new UserDTOResponse(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAge())).toList();
    }

    public UserDTOResponse buscarPorId(Long id){
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new UserDTOResponse(user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAge());
    }

}

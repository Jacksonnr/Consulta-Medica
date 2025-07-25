package projeto_tomorrow.demo.Service;

import org.springframework.stereotype.Service;
import projeto_tomorrow.demo.DTO.UserDTO.UserDTORequest;
import projeto_tomorrow.demo.DTO.UserDTO.UserDTOResponse;
import projeto_tomorrow.demo.Entity.User;
import projeto_tomorrow.demo.Repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserDTOResponse criar(UserDTORequest dto){

        User user = new User();
        user.setNome(dto.nome());
        user.setEmail(dto.email());
        user.setSenha(dto.senha());

        repository.save(user);

        return new UserDTOResponse(user.getId(),
                user.getNome(),
                user.getEmail(),
                user.getRole());
    }

    public List<UserDTOResponse> listarTodos(){
        return repository.findAll()
                .stream()
                .map(user -> new UserDTOResponse(user.getId(),
                user.getNome(),
                user.getEmail(),
                user.getRole())).toList();
    }

    public UserDTOResponse buscarPorId(Long id){
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return new UserDTOResponse(user.getId(),
                user.getNome(),
                user.getEmail(),
                user.getRole());
    }

    public void deletar(Long id){
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        repository.delete(user);
    }

    public UserDTOResponse atualizar(Long id, UserDTORequest dto){
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        user.setNome(dto.nome());
        user.setEmail(dto.email());
        user.setSenha(dto.senha());

        repository.save(user);

        return new UserDTOResponse(user.getId(),
                user.getNome(),
                user.getEmail(),
                user.getRole());
    }
}

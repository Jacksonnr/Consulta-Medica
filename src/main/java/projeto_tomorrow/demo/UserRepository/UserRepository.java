package projeto_tomorrow.demo.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto_tomorrow.demo.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

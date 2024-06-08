package pl.edu.pja.budget_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pja.budget_manager.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByEmail(String email);
}

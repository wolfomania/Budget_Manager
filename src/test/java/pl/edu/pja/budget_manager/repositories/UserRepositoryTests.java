package pl.edu.pja.budget_manager.repositories;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pja.budget_manager.domain.User;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .email("smth@gmail.com")
                .password("asdasd")
                .username("andrii")
                .creationDate(LocalDateTime.now())
                .build();
        userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void testFindById() {
        User user = userRepository.findUserByEmail("smth@gmail.com").get();
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo("smth@gmail.com");
    }

    @Test
    void testSaveUser() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        user.setUsername("Test User");
        userRepository.save(user);

        User foundUser = userRepository.findUserByEmail("test@example.com").get();
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("Test User");
    }

    @Test
    void testDeleteUser() {
        User user = userRepository.findUserByEmail("smth@gmail.com").get();
        userRepository.delete(user);
        assertThat(userRepository.findUserByEmail("smth@gmail.com")).isEmpty();
    }
}
package pl.edu.pja.budget_manager.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pja.budget_manager.domain.Transaction;
import pl.edu.pja.budget_manager.domain.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class TransactionRepositoryTests {

    @Autowired
    private TransactionRepository transactionRepository;

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

        Transaction transaction1 = Transaction.builder()
                .transactionId(1L)
                .user(user)
                .amount(100.0)
                .date(LocalDateTime.now().minusDays(1))
                .build();
        Transaction transaction2 = Transaction.builder()
                .transactionId(2L)
                .user(user)
                .amount(200.0)
                .date(LocalDateTime.now())
                .build();
        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);
    }

    @AfterEach
    void tearDown() {
        transactionRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void testExistsTransactionByUserEmailAndTransactionId() {
        transactionRepository.findAll().forEach(transaction -> {
            assertThat(transactionRepository.existsTransactionByUserEmailAndTransactionId(
                            "smth@gmail.com",
                            transaction.getTransactionId()
                    )
            ).isTrue();
        });
    }

    @Test
    void testFindAllByUserEmailOrderByDateAsc() {
        List<Transaction> transactions = transactionRepository.findAllByUserEmailOrderByDateAsc("smth@gmail.com");
        assertThat(transactions).hasSize(2);
        assertThat(transactions.get(0).getAmount()).isEqualTo(100);
        assertThat(transactions.get(1).getAmount()).isEqualTo(200);
    }

    @Test
    void testFindTransactionsByUserEmailAndDateBetween() {
        LocalDateTime startDate = LocalDateTime.now().minusDays(2);
        LocalDateTime endDate = LocalDateTime.now().plusMinutes(1);
        Set<Transaction> transactions = transactionRepository
                .findTransactionsByUserEmailAndDateBetween("smth@gmail.com", startDate, endDate);
        assertThat(transactions).hasSize(2);

        startDate = LocalDateTime.now().minusDays(1).plusHours(1);
        transactions = transactionRepository
                .findTransactionsByUserEmailAndDateBetween("smth@gmail.com", startDate, endDate);
        assertThat(transactions).hasSize(1);
    }

    @Test
    void testFindTransactionsByUserEmailAndDateAfterOrderByDateDesc() {
        LocalDateTime date = LocalDateTime.now().minusDays(2);
        List<Transaction> transactions = transactionRepository.findTransactionsByUserEmailAndDateAfterOrderByDateDesc("smth@gmail.com", date);
        assertThat(transactions).hasSize(2);
        assertThat(transactions.get(0).getAmount()).isEqualTo(200);
        assertThat(transactions.get(1).getAmount()).isEqualTo(100);
    }
}
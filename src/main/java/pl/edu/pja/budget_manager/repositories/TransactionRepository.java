package pl.edu.pja.budget_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pja.budget_manager.domain.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    boolean existsTransactionByUserEmailAndTransactionId(String email, Long transactionId);

    List<Transaction> findAllByUserEmailOrderByDateAsc(String email);

    Set<Transaction> findTransactionsByUserEmailAndDateBetween(String email, LocalDateTime date, LocalDateTime date2);

}

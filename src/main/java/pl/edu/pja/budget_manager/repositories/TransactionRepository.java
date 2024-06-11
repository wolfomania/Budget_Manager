package pl.edu.pja.budget_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pja.budget_manager.domain.Transaction;
import pl.edu.pja.budget_manager.domain.TransactionCategory;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    boolean existsTransactionByUserEmailAndTransactionId(String email, Long transactionId);

    Set<Transaction> findAllByUserEmail(String email);

    Set<Transaction> findTransactionsByUserEmailAndDateBetween(String email, LocalDateTime date, LocalDateTime date2);

}

package pl.edu.pja.budget_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pja.budget_manager.domain.Transaction;

import java.util.Set;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    boolean existsTransactionByUserEmailAndTransactionId(String email, Long transactionId);

    Set<Transaction> findAllByUserEmail(String email);
}

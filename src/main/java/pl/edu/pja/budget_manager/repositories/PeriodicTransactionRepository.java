package pl.edu.pja.budget_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.edu.pja.budget_manager.domain.PeriodicTransaction;

import java.util.Collection;
import java.util.List;

public interface PeriodicTransactionRepository extends JpaRepository<PeriodicTransaction, Long> {


    boolean existsPeriodicTransactionByUserEmailAndPeriodicTransactionId(String email, Long periodicTransactionId);

    List<PeriodicTransaction> findAllByUserEmailOrderByLastExecutionDateDesc(String email);

    @Query(nativeQuery = true, value = "SELECT * FROM periodic_transaction pt WHERE TIMESTAMPDIFF(SECOND, pt.lastExecutionDate, NOW()) * 1000 > pt.frequency")
    Collection<PeriodicTransaction> findPeriodicTransactionsToRenew();

}

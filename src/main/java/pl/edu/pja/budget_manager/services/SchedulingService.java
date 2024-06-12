package pl.edu.pja.budget_manager.services;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pja.budget_manager.domain.PeriodicTransaction;
import pl.edu.pja.budget_manager.repositories.PeriodicTransactionRepository;
import pl.edu.pja.budget_manager.repositories.TransactionRepository;
import pl.edu.pja.budget_manager.services.mappers.PeriodicTransactionMapper;

import java.time.LocalDateTime;
import java.util.Collection;

@Service
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SchedulingService {

    PeriodicTransactionRepository periodicTransactionRepository;

    TransactionRepository transactionRepository;

    @Scheduled(fixedRate = 5 * 60 * 1000)
    @Transactional
    public void executePeriodicTransactions() {
        Collection<PeriodicTransaction> periodicTransactions = periodicTransactionRepository.findPeriodicTransactionsToRenew();
        periodicTransactions.stream()
                .map(periodicTransaction -> {
                    periodicTransaction = periodicTransaction.withLastExecutionDate(LocalDateTime.now());
                    periodicTransactionRepository.save(periodicTransaction);
                    return periodicTransaction;
                })
                .map(PeriodicTransactionMapper::mapToTransaction)
                .forEach(transactionRepository::save);
    }
}

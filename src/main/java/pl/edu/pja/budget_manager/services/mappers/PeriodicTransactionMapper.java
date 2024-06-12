package pl.edu.pja.budget_manager.services.mappers;

import pl.edu.pja.budget_manager.domain.*;
import pl.edu.pja.budget_manager.web.rest.request.AddUserPeriodicTransactionReq;
import pl.edu.pja.budget_manager.web.rest.request.PatchPeriodicTransactionReq;
import pl.edu.pja.budget_manager.web.rest.request.PatchTransactionReq;
import pl.edu.pja.budget_manager.web.rest.response.PeriodicTransactionRes;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

public class PeriodicTransactionMapper {

    public static PeriodicTransaction mapFromAddUserPeriodicTransactionReq(AddUserPeriodicTransactionReq req, User user, TransactionCategory category, Currency currency) {
        return PeriodicTransaction.builder()
                .amount(req.getAmount())
                .frequency(req.getFrequency())
                .description(req.getDescription())
                .creationDate(LocalDateTime.now())
                .lastExecutionDate(LocalDateTime.now())
                .category(category)
                .currency(currency)
                .user(user)
                .build();
    }

    public static PeriodicTransaction mapFromPatchRequest(
            PatchPeriodicTransactionReq patchPeriodicTransactionReq,
            PeriodicTransaction transaction,
            TransactionCategory category,
            Currency currency
    ) {
        return PeriodicTransaction.builder()
                .periodicTransactionId(transaction.getPeriodicTransactionId())
                .amount(patchPeriodicTransactionReq.getAmount() != null ? patchPeriodicTransactionReq.getAmount() : transaction.getAmount())
                .frequency(patchPeriodicTransactionReq.getFrequency() != null ? patchPeriodicTransactionReq.getFrequency() : transaction.getFrequency())
                .description(patchPeriodicTransactionReq.getDescription() != null ? patchPeriodicTransactionReq.getDescription() : transaction.getDescription())
                .category(category != null ? category : transaction.getCategory())
                .currency(currency != null ? currency : transaction.getCurrency())
                .lastExecutionDate(transaction.getLastExecutionDate())
                .creationDate(transaction.getCreationDate())
                .user(transaction.getUser())
                .build();
    }

    public static PeriodicTransactionRes mapToPeriodicTransactionRes(PeriodicTransaction transaction) {
        return PeriodicTransactionRes.builder()
                .periodicTransactionId(transaction.getPeriodicTransactionId())
                .amount(transaction.getAmount())
                .frequency(transaction.getFrequency())
                .creationDate(transaction.getCreationDate())
                .lastExecutionDate(transaction.getLastExecutionDate())
                .description(transaction.getDescription())
                .category(transaction.getCategory().getName())
                .currency(transaction.getCurrency().getName())
                .email(transaction.getUser().getEmail())
                .build();
    }

    public static Collection<PeriodicTransactionRes> mapToPeriodicTransactionResCollection(Collection<PeriodicTransaction> transactions) {
        return transactions.stream()
                .map(PeriodicTransactionMapper::mapToPeriodicTransactionRes)
                .toList();
    }

    public static Transaction mapToTransaction(PeriodicTransaction periodicTransaction) {
        return Transaction.builder()
                .amount(periodicTransaction.getAmount())
                .description(periodicTransaction.getDescription())
                .date(LocalDateTime.now())
                .category(periodicTransaction.getCategory())
                .currency(periodicTransaction.getCurrency())
                .user(periodicTransaction.getUser())
                .build();
    }
}

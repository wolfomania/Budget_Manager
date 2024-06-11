package pl.edu.pja.budget_manager.services.mappers;

import lombok.NoArgsConstructor;
import pl.edu.pja.budget_manager.domain.Currency;
import pl.edu.pja.budget_manager.domain.Transaction;
import pl.edu.pja.budget_manager.domain.TransactionCategory;
import pl.edu.pja.budget_manager.domain.User;
import pl.edu.pja.budget_manager.web.rest.request.AddUserTransactionReq;
import pl.edu.pja.budget_manager.web.rest.request.PatchTransactionReq;
import pl.edu.pja.budget_manager.web.rest.response.TransactionRes;
import pl.edu.pja.budget_manager.web.rest.response.UserSummaryRes;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class TransactionMapper {

    public static Transaction mapFromAddUserTransactionReq(AddUserTransactionReq addUserTransactionReq, User user, TransactionCategory transactionCategory, Currency currency) {
        return Transaction.builder()
                .amount(addUserTransactionReq.getAmount())
                .date(addUserTransactionReq.getDate())
                .description(addUserTransactionReq.getDescription())
                .category(transactionCategory)
                .currency(currency)
                .user(user)
                .build();
    }

    public static TransactionRes mapToTransactionRes(Transaction transaction) {
        return TransactionRes.builder()
                .transactionId(transaction.getTransactionId())
                .amount(transaction.getAmount())
                .date(transaction.getDate())
                .description(transaction.getDescription())
                .category(transaction.getCategory() == null ? null : transaction.getCategory().getName())
                .currency(transaction.getCurrency() == null ? null : transaction.getCurrency().getName())
                .email(transaction.getUser().getEmail())
                .build();
    }

    public static Collection<TransactionRes> mapToTransactionResCollection(Collection<Transaction> transactions) {
        return transactions.stream()
                .map(TransactionMapper::mapToTransactionRes)
                .collect(Collectors.toSet());
    }

    public static UserSummaryRes mapToUserSummaryRes(Map<String, Set<Transaction>> transactionCategorySetMap) {
        Map<String, Double> result = new HashMap<>();

        transactionCategorySetMap.forEach((key, value) -> {
            Double sum = value.stream()
                    .map(Transaction::getAmount)
                    .reduce(0.0, Double::sum);
            result.put(key, sum);
        });

        return UserSummaryRes.of(result);
    }

    public static Transaction patchFromTransactionReq(
            PatchTransactionReq patchTransactionReq,
            Transaction transaction,
            TransactionCategory transactionCategory,
            Currency currency
    )
    {
        return Transaction.builder()
                .transactionId(transaction.getTransactionId())
                .amount(patchTransactionReq.getAmount() == null ? transaction.getAmount() : patchTransactionReq.getAmount())
                .date(patchTransactionReq.getDate() == null ? transaction.getDate() : patchTransactionReq.getDate())
                .description(patchTransactionReq.getDescription() == null ? transaction.getDescription() : patchTransactionReq.getDescription())
                .category(transactionCategory == null ? transaction.getCategory() : transactionCategory)
                .currency(currency == null ? transaction.getCurrency() : currency)
                .user(transaction.getUser())
                .build();
    }
}

package pl.edu.pja.budget_manager.services.mappers;

import pl.edu.pja.budget_manager.domain.Currency;
import pl.edu.pja.budget_manager.domain.Transaction;
import pl.edu.pja.budget_manager.domain.TransactionCategory;
import pl.edu.pja.budget_manager.domain.User;
import pl.edu.pja.budget_manager.web.rest.request.AddUserTransactionReq;
import pl.edu.pja.budget_manager.web.rest.response.TransactionRes;

import java.util.Collection;
import java.util.stream.Collectors;

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
                .category(transaction.getCategory().getName())
                .currency(transaction.getCurrency().getName())
                .email(transaction.getUser().getEmail())
                .build();
    }

    public static Collection<TransactionRes> mapToTransactionResCollection(Collection<Transaction> transactions) {
        return transactions.stream()
                .map(TransactionMapper::mapToTransactionRes)
                .collect(Collectors.toSet());
    }
}

package pl.edu.pja.budget_manager.services;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pja.budget_manager.domain.Currency;
import pl.edu.pja.budget_manager.domain.Transaction;
import pl.edu.pja.budget_manager.domain.TransactionCategory;
import pl.edu.pja.budget_manager.domain.User;
import pl.edu.pja.budget_manager.repositories.CurrencyRepository;
import pl.edu.pja.budget_manager.repositories.TransactionCategoryRepository;
import pl.edu.pja.budget_manager.repositories.TransactionRepository;
import pl.edu.pja.budget_manager.repositories.UserRepository;
import pl.edu.pja.budget_manager.security.UserPrincipal;
import pl.edu.pja.budget_manager.services.mappers.TransactionMapper;
import pl.edu.pja.budget_manager.web.rest.request.AddUserTransactionReq;
import pl.edu.pja.budget_manager.web.rest.request.PatchTransactionReq;
import pl.edu.pja.budget_manager.web.rest.response.TransactionRes;

import java.util.Collection;

@Service
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserTransactionService {

    TransactionRepository transactionRepository;

    TransactionCategoryRepository transactionCategoryRepository;

    CurrencyRepository currencyRepository;

    UserRepository userRepository;

    public Collection<Transaction> getUserTransactions(String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email: " + email + " doesn't exist."));
        return transactionRepository.findAllByUserEmail(user.getEmail());
    }

    @Transactional
    public Transaction addTransaction(AddUserTransactionReq addUserTransactionReq) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userPrincipal.getUser();

        TransactionCategory category = transactionCategoryRepository.findById(addUserTransactionReq.getCategoryId())
                .orElseThrow();

        Currency currency = currencyRepository.findById(addUserTransactionReq.getCurrencyId())
                .orElseThrow();

        Transaction transaction = TransactionMapper.mapFromAddUserTransactionReq(addUserTransactionReq, user, category, currency);
        return transactionRepository.save(transaction);
    }

    @Transactional
    public void deleteTransaction(Long transactionId) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userPrincipal.getUser();
        if (!transactionRepository.existsTransactionByUserEmailAndTransactionId(user.getEmail(), transactionId))
            throw new IllegalArgumentException("User don't have transaction with id: " + transactionId + " or transaction doesn't exist.");
        transactionRepository.deleteById(transactionId);
    }

    public Transaction getTransaction(Long transactionId) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userPrincipal.getUser();
        return transactionRepository.findById(transactionId).map(transaction -> {
            if (!transaction.getUser().getEmail().equals(user.getEmail()))
                throw new IllegalArgumentException("User don't have transaction with id: " + transactionId + ".");
            return transaction;
        }).orElseThrow(() -> new IllegalArgumentException("Transaction with id: " + transactionId + " doesn't exist."));
    }

    @Transactional
    public TransactionRes updateTransaction(Long transactionId, PatchTransactionReq patchTransactionReq) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userPrincipal.getUser();

        if (!transactionRepository.existsTransactionByUserEmailAndTransactionId(user.getEmail(), transactionId))
            throw new IllegalArgumentException("User don't have transaction with id: " + transactionId + " or transaction doesn't exist.");

        Transaction transaction = transactionRepository.findById(transactionId).orElseThrow();

        TransactionCategory category = null;
        if(patchTransactionReq.getCategoryId() != null) {
            category = transactionCategoryRepository.findById(patchTransactionReq.getCategoryId())
                    .orElseThrow();
        }

        Currency currency = null;
        if(patchTransactionReq.getCurrencyId() != null) {
            currency = currencyRepository.findById(patchTransactionReq.getCurrencyId())
                    .orElseThrow();
        }

        transaction = TransactionMapper.patchFromTransactionReq(patchTransactionReq, transaction, category, currency);

        transactionRepository.save(transaction);
        return TransactionMapper.mapToTransactionRes(transaction);
    }
}

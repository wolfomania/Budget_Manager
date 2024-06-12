package pl.edu.pja.budget_manager.services;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pja.budget_manager.domain.*;
import pl.edu.pja.budget_manager.repositories.CurrencyRepository;
import pl.edu.pja.budget_manager.repositories.PeriodicTransactionRepository;
import pl.edu.pja.budget_manager.repositories.TransactionCategoryRepository;
import pl.edu.pja.budget_manager.repositories.UserRepository;
import pl.edu.pja.budget_manager.security.UserPrincipal;
import pl.edu.pja.budget_manager.services.mappers.PeriodicTransactionMapper;
import pl.edu.pja.budget_manager.web.rest.request.AddUserPeriodicTransactionReq;
import pl.edu.pja.budget_manager.web.rest.request.PatchPeriodicTransactionReq;
import pl.edu.pja.budget_manager.web.rest.response.PeriodicTransactionRes;

import java.util.Collection;

@Service
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserPeriodicTransactionService {

    PeriodicTransactionRepository periodicTransactionRepository;

    TransactionCategoryRepository transactionCategoryRepository;

    CurrencyRepository currencyRepository;

    UserRepository userRepository;

    public Collection<PeriodicTransaction> getUserPeriodicTransactions(String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email: " + email + " doesn't exist."));
        return periodicTransactionRepository.findAllByUserEmailOrderByLastExecutionDateDesc(user.getEmail());
    }

    @Transactional
    public PeriodicTransaction addTransaction(AddUserPeriodicTransactionReq addUserPeriodicTransactionReq) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userPrincipal.getUser();

        TransactionCategory category = transactionCategoryRepository.findById(addUserPeriodicTransactionReq.getCategoryId())
                .orElseThrow();

        Currency currency = currencyRepository.findById(addUserPeriodicTransactionReq.getCurrencyId())
                .orElseThrow();

        PeriodicTransaction transaction = PeriodicTransactionMapper.mapFromAddUserPeriodicTransactionReq(addUserPeriodicTransactionReq, user, category, currency);
        return periodicTransactionRepository.save(transaction);
    }

    @Transactional
    public void deletePeriodicTransaction(Long periodicTransactionId) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userPrincipal.getUser();
        if (!periodicTransactionRepository.existsPeriodicTransactionByUserEmailAndPeriodicTransactionId(user.getEmail(), periodicTransactionId))
            throw new IllegalArgumentException("User don't have periodic transaction with id: " + periodicTransactionId + " or it doesn't exist.");
        periodicTransactionRepository.deleteById(periodicTransactionId);
    }

    public PeriodicTransaction getPeriodicTransaction(Long transactionId) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userPrincipal.getUser();
        return periodicTransactionRepository.findById(transactionId).map(transaction -> {
            if (!transaction.getUser().getEmail().equals(user.getEmail()))
                throw new IllegalArgumentException("User don't have periodic transaction with id: " + transactionId + ".");
            return transaction;
        }).orElseThrow(() -> new IllegalArgumentException("Periodic transaction with id: " + transactionId + " doesn't exist."));
    }

    @Transactional
    public PeriodicTransactionRes updatePeriodicTransaction(Long transactionId, PatchPeriodicTransactionReq patchPeriodicTransactionReq) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userPrincipal.getUser();

        if (!periodicTransactionRepository.existsPeriodicTransactionByUserEmailAndPeriodicTransactionId(user.getEmail(), transactionId))
            throw new IllegalArgumentException("User don't have periodic transaction with id: " + transactionId + " or it doesn't exist.");

        PeriodicTransaction transaction = periodicTransactionRepository.findById(transactionId).orElseThrow();

        TransactionCategory category = null;
        if(patchPeriodicTransactionReq.getCategoryId() != null) {
            category = transactionCategoryRepository.findById(patchPeriodicTransactionReq.getCategoryId())
                    .orElseThrow();
        }

        Currency currency = null;
        if(patchPeriodicTransactionReq.getCurrencyId() != null) {
            currency = currencyRepository.findById(patchPeriodicTransactionReq.getCurrencyId())
                    .orElseThrow();
        }

        transaction = PeriodicTransactionMapper.mapFromPatchRequest(patchPeriodicTransactionReq, transaction, category, currency);

        periodicTransactionRepository.save(transaction);
        return PeriodicTransactionMapper.mapToPeriodicTransactionRes(transaction);
    }
}

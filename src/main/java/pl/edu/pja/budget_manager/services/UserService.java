package pl.edu.pja.budget_manager.services;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pja.budget_manager.domain.*;
import pl.edu.pja.budget_manager.repositories.*;
import pl.edu.pja.budget_manager.security.UserPrincipal;
import pl.edu.pja.budget_manager.services.mappers.TransactionMapper;
import pl.edu.pja.budget_manager.services.mappers.UserMapper;
import pl.edu.pja.budget_manager.web.rest.request.AddUserTransactionReq;
import pl.edu.pja.budget_manager.web.rest.request.UserPatchReq;
import pl.edu.pja.budget_manager.web.rest.response.UserProfileRes;
import pl.edu.pja.budget_manager.web.rest.response.UserSummaryRes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserService {

    NotificationRepository notificationRepository;

    PasswordEncoder passwordEncoder;

    CurrencyRepository currencyRepository;

    UserRepository userRepository;

    TransactionRepository transactionRepository;

    TransactionCategoryRepository transactionCategoryRepository;

    public UserProfileRes getUserProfile() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userPrincipal.getUser();
        Set<Notification> notifications = notificationRepository.findAllByUserEmail(user.getEmail());
        return UserMapper.mapToUserProfileRes(user, notifications);
    }

    @Transactional
    public UserProfileRes updateUserProfile(UserPatchReq userPatchReq) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userPrincipal.getUser();
        Currency currency  = currencyRepository.findById(userPatchReq.getPreferredCurrencyId())
                    .orElse(null);
        if(userPatchReq.getPassword() != null)
            userPatchReq = userPatchReq.withPassword(passwordEncoder.encode(userPatchReq.getPassword()));
        user = UserMapper.mapUserPatchReqToUser(userPatchReq, user, currency);
        userRepository.save(user);
        return UserMapper.mapToUserProfileRes(user, notificationRepository.findAllByUserEmail(user.getEmail()));
    }

    public Collection<Notification> getUserNotifications() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userPrincipal.getUser();
        return notificationRepository.findAllByUserEmail(user.getEmail());
    }

    public Collection<Transaction> getUserTransactions() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userPrincipal.getUser();
        return transactionRepository.findAllByUserEmail(user.getEmail());
    }

    @Transactional
    public Transaction addTransaction(AddUserTransactionReq addUserTransactionReq) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userPrincipal.getUser();
        TransactionCategory category = transactionCategoryRepository.findById(addUserTransactionReq.getCategoryId())
                .orElse(null);
        Currency currency = currencyRepository.findById(addUserTransactionReq.getCurrencyId())
                .orElse(null);
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

    public void deleteNotification(Long notificationId) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userPrincipal.getUser();
        if (!notificationRepository.existsNotificationByUserEmailAndNotificationId(user.getEmail(), notificationId))
            throw new IllegalArgumentException("User don't have notification with id: " + notificationId + " or notification doesn't exist.");
        notificationRepository.deleteById(notificationId);
    }

    public UserSummaryRes getSummary(LocalDateTime startDate, LocalDateTime endDate) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userPrincipal.getUser();
        Set<Transaction> transactions = transactionRepository.findTransactionsByUserEmailAndDateBetween(user.getEmail(), startDate, endDate);

        Map<String, Set<Transaction>> transactionCategorySetMap = transactions.stream()
                .collect(Collectors.groupingBy(transaction -> transaction.getCategory().getName(), Collectors.toSet()));

        return TransactionMapper.mapToUserSummaryRes(transactionCategorySetMap);
    }
}

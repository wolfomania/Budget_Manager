package pl.edu.pja.budget_manager.web.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.budget_manager.domain.Transaction;
import pl.edu.pja.budget_manager.services.UserTransactionService;
import pl.edu.pja.budget_manager.services.mappers.TransactionMapper;
import pl.edu.pja.budget_manager.web.rest.request.AddUserTransactionReq;
import pl.edu.pja.budget_manager.web.rest.request.PatchTransactionReq;
import pl.edu.pja.budget_manager.web.rest.response.TransactionRes;

import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping("/api/user/transactions")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserTransactionController {

    UserTransactionService userTransactionService;

    @GetMapping()
    public ResponseEntity<Collection<TransactionRes>> getUserTransactions(Principal principal) {
        Collection<Transaction> transactions = userTransactionService.getUserTransactions(principal.getName());
        Collection<TransactionRes> transactionRes = TransactionMapper.mapToTransactionResCollection(transactions);
        return ResponseEntity.ok(transactionRes);
    }

    @PostMapping()
    public ResponseEntity<TransactionRes> addTransaction(@Valid @RequestBody AddUserTransactionReq addUserTransactionReq) {
        Transaction transaction = userTransactionService.addTransaction(addUserTransactionReq);
        return ResponseEntity.ok(TransactionMapper.mapToTransactionRes(transaction));
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionRes> getTransaction(@PathVariable Long transactionId) {
        Transaction transaction = userTransactionService.getTransaction(transactionId);
        return ResponseEntity.ok(TransactionMapper.mapToTransactionRes(transaction));
    }

    @PatchMapping("/{transactionId}")
    public ResponseEntity<TransactionRes> updateTransaction(@PathVariable Long transactionId, @Valid @RequestBody PatchTransactionReq patchTransactionReq) {
        return ResponseEntity.ok(userTransactionService.updateTransaction(transactionId, patchTransactionReq));
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long transactionId) {
        userTransactionService.deleteTransaction(transactionId);
        return ResponseEntity.noContent().build();
    }
}

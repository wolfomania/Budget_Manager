package pl.edu.pja.budget_manager.web.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.budget_manager.domain.PeriodicTransaction;
import pl.edu.pja.budget_manager.services.UserPeriodicTransactionService;
import pl.edu.pja.budget_manager.services.mappers.PeriodicTransactionMapper;
import pl.edu.pja.budget_manager.services.mappers.TransactionMapper;
import pl.edu.pja.budget_manager.web.rest.request.AddUserPeriodicTransactionReq;
import pl.edu.pja.budget_manager.web.rest.request.PatchPeriodicTransactionReq;
import pl.edu.pja.budget_manager.web.rest.request.PatchTransactionReq;
import pl.edu.pja.budget_manager.web.rest.response.PeriodicTransactionRes;

import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping("/api/user/periodic-transactions")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserPeriodicTransactionController {

    UserPeriodicTransactionService userPeriodicTransactionService;


    @GetMapping()
    public ResponseEntity<Collection<PeriodicTransactionRes>> getUserTransactions(Principal principal) {
        Collection<PeriodicTransaction> transactions = userPeriodicTransactionService.getUserPeriodicTransactions(principal.getName());
        Collection<PeriodicTransactionRes> transactionRes = PeriodicTransactionMapper.mapToPeriodicTransactionResCollection(transactions);
        return ResponseEntity.ok(transactionRes);
    }

    @PostMapping()
    public ResponseEntity<PeriodicTransactionRes> addTransaction(@Valid @RequestBody AddUserPeriodicTransactionReq addUserPeriodicTransactionReq) {
        PeriodicTransaction transaction = userPeriodicTransactionService.addTransaction(addUserPeriodicTransactionReq);
        return ResponseEntity.ok(PeriodicTransactionMapper.mapToPeriodicTransactionRes(transaction));
    }

    @GetMapping("/{periodicTransactionId}")
    public ResponseEntity<PeriodicTransactionRes> getTransaction(@PathVariable Long periodicTransactionId) {
        PeriodicTransaction transaction = userPeriodicTransactionService.getPeriodicTransaction(periodicTransactionId);
        return ResponseEntity.ok(PeriodicTransactionMapper.mapToPeriodicTransactionRes(transaction));
    }

    @PatchMapping("/{transactionId}")
    public ResponseEntity<PeriodicTransactionRes> updateTransaction(@PathVariable Long transactionId, @Valid @RequestBody PatchPeriodicTransactionReq patchPeriodicTransactionReq) {
        return ResponseEntity.ok(userPeriodicTransactionService.updatePeriodicTransaction(transactionId, patchPeriodicTransactionReq));
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long transactionId) {
        userPeriodicTransactionService.deletePeriodicTransaction(transactionId);
        return ResponseEntity.noContent().build();
    }
}

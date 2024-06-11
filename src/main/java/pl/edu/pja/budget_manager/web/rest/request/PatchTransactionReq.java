package pl.edu.pja.budget_manager.web.rest.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.edu.pja.budget_manager.constraints.CurrencyWithIdExist;
import pl.edu.pja.budget_manager.constraints.TransactionCategoryWithIdExist;

import java.time.LocalDateTime;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Getter
public class PatchTransactionReq {

    @Min(0)
    Double amount;

    @Column(columnDefinition = "TIMESTAMP")
    @Past
    LocalDateTime date;

    String description;

    @TransactionCategoryWithIdExist
    Long categoryId;

    @CurrencyWithIdExist
    Long currencyId;
}
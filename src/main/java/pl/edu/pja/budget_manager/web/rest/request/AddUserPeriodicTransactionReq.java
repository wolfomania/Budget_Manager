package pl.edu.pja.budget_manager.web.rest.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import lombok.NonNull;
import lombok.Value;
import pl.edu.pja.budget_manager.constraints.CurrencyWithIdExist;
import pl.edu.pja.budget_manager.constraints.TransactionCategoryWithIdExist;

import java.time.LocalDateTime;

@Value
public class AddUserPeriodicTransactionReq {

    @Min(0)
    Double amount;

    @NonNull
    @Min(value = 24 * 60 * 60 * 1000, message = "Frequency must be at least daily (86400000)")
    Long frequency;

    String description;

    @NonNull
    @TransactionCategoryWithIdExist
    Long categoryId;

    @NonNull
    @CurrencyWithIdExist
    Long currencyId;
}

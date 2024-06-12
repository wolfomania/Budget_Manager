package pl.edu.pja.budget_manager.web.rest.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.edu.pja.budget_manager.constraints.CurrencyWithIdExist;
import pl.edu.pja.budget_manager.constraints.TransactionCategoryWithIdExist;

import java.time.LocalDateTime;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Getter
public class PatchPeriodicTransactionReq {

    @Min(0)
    Double amount;

    @Min(value = 24 * 60 * 60 * 1000, message = "Frequency must be at least daily (86400000)")
    Long frequency;

    String description;

    @TransactionCategoryWithIdExist
    Long categoryId;

    @CurrencyWithIdExist
    Long currencyId;
}

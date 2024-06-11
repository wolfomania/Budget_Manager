package pl.edu.pja.budget_manager.web.rest.request;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.edu.pja.budget_manager.domain.Currency;
import pl.edu.pja.budget_manager.domain.TransactionCategory;

import java.time.LocalDateTime;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Getter
public class AddUserTransactionReq {

    @Min(0)
    Double amount;

    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime date;

    String description;

    @NonNull
    Long categoryId;

    @NonNull
    Long currencyId;
}

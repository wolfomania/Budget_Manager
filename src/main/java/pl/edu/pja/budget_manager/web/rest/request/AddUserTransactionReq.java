package pl.edu.pja.budget_manager.web.rest.request;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aspectj.lang.annotation.Before;
import org.springframework.data.annotation.CreatedDate;
import pl.edu.pja.budget_manager.constraints.CurrencyWithIdExist;
import pl.edu.pja.budget_manager.constraints.TransactionCategoryWithIdExist;
import pl.edu.pja.budget_manager.domain.Currency;
import pl.edu.pja.budget_manager.domain.TransactionCategory;

import java.time.LocalDateTime;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Getter
@Builder
public class AddUserTransactionReq {

    @Min(0)
    Double amount;

    @Column(columnDefinition = "TIMESTAMP")
    @Past
    LocalDateTime date;

    String description;

    @NonNull
    @TransactionCategoryWithIdExist
    Long categoryId;

    @NonNull
    @CurrencyWithIdExist
    Long currencyId;
}

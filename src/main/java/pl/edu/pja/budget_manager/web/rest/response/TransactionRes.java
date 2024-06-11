package pl.edu.pja.budget_manager.web.rest.response;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import pl.edu.pja.budget_manager.domain.Currency;
import pl.edu.pja.budget_manager.domain.TransactionCategory;
import pl.edu.pja.budget_manager.domain.User;

import java.time.LocalDateTime;

@Builder
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Getter
public class TransactionRes {

    Long transactionId;

    Double amount;

    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime date;

    String description;

    String category;

    String currency;

    String email;
}

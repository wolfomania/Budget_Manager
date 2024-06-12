package pl.edu.pja.budget_manager.web.rest.response;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class PeriodicTransactionRes {

    Long periodicTransactionId;

    Double amount;

    Long frequency;

    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime creationDate;

    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime lastExecutionDate;

    String description;

    String category;

    String currency;

    String email;
}

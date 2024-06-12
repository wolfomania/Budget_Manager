package pl.edu.pja.budget_manager.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "periodic_transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PeriodicTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "periodic_transaction_id")
    Long periodicTransactionId;

    private Double amount;

    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime creationDate;

    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime lastExecutionDate;

    Long frequency;

    String description;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    TransactionCategory category;

    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "currency_id")
    Currency currency;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    User user;

}
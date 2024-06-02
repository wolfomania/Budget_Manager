package pl.edu.pja.budget_manager.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "periodic_transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PeriodicTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "periodic_transaction_id")
    private Long periodicTransactionId;

    private Double amount;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime creationDate;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private TransactionCategory category;

    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "currency_id")
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

}
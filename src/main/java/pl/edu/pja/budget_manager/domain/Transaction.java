package pl.edu.pja.budget_manager.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    Long transactionId;

    Double amount;
    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime date;
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

    /*@OneToMany(
            mappedBy = "transaction",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            targetEntity = GroupTransaction.class
    )
    Set<GroupTransaction> groups;*/
}
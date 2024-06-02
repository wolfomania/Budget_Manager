package pl.edu.pja.budget_manager.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "group_transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long groupTransactionId;

    @ManyToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "transaction_id")
    Transaction transaction;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    BudgetGroup group;
}
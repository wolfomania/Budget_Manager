package pl.edu.pja.budget_manager.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.edu.pja.budget_manager.domain.keys.GroupTransactionKey;

@Entity
@Table(name = "group_transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupTransaction {

   /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long groupTransactionId;*/

    @EmbeddedId
    GroupTransactionKey groupTransactionId;

    @ManyToOne
    @MapsId("transaction_id")
    @JoinColumn(name = "transaction_id", referencedColumnName = "transaction_id")
    Transaction transaction;

    @ManyToOne
    @MapsId("group_id")
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    BudgetGroup group;
}

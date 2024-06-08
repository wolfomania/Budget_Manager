package pl.edu.pja.budget_manager.domain.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class DebtKey implements Serializable {

    @Column(name = "user_id")
    String userId;

    @Column(name = "group_transaction_id")
    GroupTransactionKey groupTransactionId;
}

package pl.edu.pja.budget_manager.domain.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class GroupTransactionKey implements Serializable {

    @Column(name = "transaction_id")
    private Long transactionId;
    @Column(name = "group_id")
    private Long groupId;
}

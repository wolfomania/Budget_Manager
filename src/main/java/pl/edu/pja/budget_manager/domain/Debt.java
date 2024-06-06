package pl.edu.pja.budget_manager.domain;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.edu.pja.budget_manager.domain.keys.DebtKey;

@Entity
@Table(name = "debt")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Debt {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long deptId;

    @EmbeddedId
    @Column(name = "debt_id")
    DebtKey debtId;

    @NonNull
    @MapsId("userId")
    @ManyToOne
    User user;

    @NonNull
    @MapsId("groupTransactionId")
    @ManyToOne
    GroupTransaction groupTransaction;

    @NonNull
    Double amount;
}

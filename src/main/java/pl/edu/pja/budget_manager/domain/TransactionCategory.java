package pl.edu.pja.budget_manager.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transaction_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @NonNull
    private String name;
}

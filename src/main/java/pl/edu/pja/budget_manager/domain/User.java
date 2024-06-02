package pl.edu.pja.budget_manager.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @Column(name = "user_id", unique = true)
    String email;

    @NonNull
    @ToString.Exclude
    String password;

    String nickname;

    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "currency_id")
    Currency preferredCurrency;

    @NonNull
    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime creationDate;

    String name;
    String surname;

    @ToString.Exclude
    @OneToMany(mappedBy = "user",
            orphanRemoval = true,
            cascade = {
                    CascadeType.ALL
            })
    Set<Notification> notifications;

    @OneToMany(mappedBy = "user",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH
            })
    Set<Transaction> transactions;

    @ManyToMany(mappedBy = "users")
    Set<BudgetGroup> groups;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            targetEntity = GroupApplication.class
    )
    Set<GroupApplication> applications;

}

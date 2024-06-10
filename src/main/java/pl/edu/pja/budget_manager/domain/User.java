package pl.edu.pja.budget_manager.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
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
    @JsonIgnore
    String password;

    String username;

    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "currency_id")
    Currency preferredCurrency;

    @NonNull
    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime creationDate;

    String firstName;
    String lastName;

    /*@ToString.Exclude
    @OneToMany(mappedBy = "user",
            orphanRemoval = true,
            cascade = {
                    CascadeType.ALL
            })
    Set<Notification> notifications;

    @ToString.Exclude
    @OneToMany(mappedBy = "user",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
                    CascadeType.REFRESH
            })
    Set<Transaction> transactions;*/

    @ToString.Exclude
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    Set<BudgetGroup> groups;

    /*@ToString.Exclude
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            targetEntity = GroupApplication.class,
            fetch = FetchType.EAGER
    )
    Set<GroupApplication> applications;*/
}

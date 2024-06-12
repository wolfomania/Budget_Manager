package pl.edu.pja.budget_manager.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "budget_group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BudgetGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    Long groupId;

    String name;

    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime creationDate;

    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime lastModificationDate;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "user_id")
    User creator;

    @OneToMany(
            mappedBy = "group",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            targetEntity = GroupInvitation.class,
            fetch = FetchType.EAGER
    )
    Set<GroupInvitation> invitations;

    @ManyToMany
    @JoinTable(
            name = "group_user",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    Set<User> users;

    @OneToMany(
            mappedBy = "group",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            targetEntity = GroupTransaction.class
    )
    Set<GroupTransaction> transactions;

    @OneToMany(
            mappedBy = "group",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            targetEntity = GroupApplication.class
    )
    Set<GroupApplication> applications;
}

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

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "user_id")
    User creator;

    @OneToMany(
            mappedBy = "group",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    Set<GroupInvitation> invitations;

    @ManyToMany(mappedBy = "groups")
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
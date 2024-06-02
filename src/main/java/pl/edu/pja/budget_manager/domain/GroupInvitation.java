package pl.edu.pja.budget_manager.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "group_invitation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupInvitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long invitationId;


    String link;
    String expirationDate;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    BudgetGroup group;
}

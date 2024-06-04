package pl.edu.pja.budget_manager.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.edu.pja.budget_manager.domain.keys.UserGroupKey;

import java.time.LocalDateTime;

@Entity
@Table(name = "group_application")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupApplication {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long applicationId;*/
    @EmbeddedId
    UserGroupKey userGroupKey;

    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime date;
    String applicationText;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    User user;

    @ManyToOne
    @MapsId("group_id")
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    BudgetGroup group;


}

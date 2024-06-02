package pl.edu.pja.budget_manager.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "group_application")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long applicationId;

    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime date;
    String applicationText;

    @ManyToOne
    @Column(name = "user_id")
    User user;

    @ManyToOne
    @Column(name = "group_id")
    BudgetGroup group;
}
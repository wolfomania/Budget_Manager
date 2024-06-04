package pl.edu.pja.budget_manager.domain.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class UserGroupKey implements Serializable {

    @Column(name = "user_id")
    String userId;

    @Column(name = "group_id")
    Long groupId;
}

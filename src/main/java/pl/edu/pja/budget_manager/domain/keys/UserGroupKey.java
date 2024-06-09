package pl.edu.pja.budget_manager.domain.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UserGroupKey implements Serializable {

    @Column(name = "user_id")
    String userId;

    @Column(name = "group_id")
    Long groupId;
}

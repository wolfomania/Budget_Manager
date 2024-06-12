package pl.edu.pja.budget_manager.web.rest.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.edu.pja.budget_manager.domain.Currency;

import java.time.LocalDateTime;

@With
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Getter
@Builder
@ToString
public class UserSignUpRes {

    String email;

    //TODO delete this later
    @JsonIgnore
    String password;

    String username;

    Currency preferredCurrency;

    LocalDateTime creationDate;

    LocalDateTime lastLogInDate;

    String firstName;

    String lastName;
}

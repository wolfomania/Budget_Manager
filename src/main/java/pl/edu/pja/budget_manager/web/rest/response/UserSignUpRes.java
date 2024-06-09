package pl.edu.pja.budget_manager.web.rest.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import pl.edu.pja.budget_manager.domain.Currency;

import java.time.LocalDateTime;

@With
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Builder
@ToString
public class UserSignUpRes {

    String email;

    //TODO delete this later
    String password;

    String username;

    Currency preferredCurrency;

    LocalDateTime creationDate;

    String firstName;

    String lastName;
}

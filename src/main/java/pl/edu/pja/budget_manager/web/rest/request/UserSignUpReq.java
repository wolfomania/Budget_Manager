package pl.edu.pja.budget_manager.web.rest.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.NonNull;
import lombok.Value;
import pl.edu.pja.budget_manager.constraints.UniqueEmail;

@Value
public class UserSignUpReq {

    @NonNull
    @NotBlank(message = "User email can not be empty")
    @Email(message = "User email should be valid", regexp = ".*@.*\\..*")
    @UniqueEmail
    String email;

    @NonNull
    @Size(min = 12, message = "User password lengths can not be less than 12")
    String password;

    String username;

    Long preferredCurrencyId;

    String firstName;

    String lastName;


}

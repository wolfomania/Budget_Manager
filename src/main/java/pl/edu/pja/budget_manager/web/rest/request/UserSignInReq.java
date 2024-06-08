package pl.edu.pja.budget_manager.web.rest.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class UserSignInReq {

    @Email(message = "User email should be valid", regexp = ".*@.*\\..*")
    String email;

    @NotBlank
    String password;
}

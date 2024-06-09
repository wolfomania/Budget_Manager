package pl.edu.pja.budget_manager.web.rest.response;

import lombok.Value;

@Value(staticConstructor = "of")
public class TokenRes {

    String token;

    long expiresIn;
}

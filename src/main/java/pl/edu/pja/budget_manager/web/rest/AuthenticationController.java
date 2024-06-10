package pl.edu.pja.budget_manager.web.rest;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pja.budget_manager.domain.User;
import pl.edu.pja.budget_manager.services.AuthenticationService;
import pl.edu.pja.budget_manager.web.rest.request.UserSignInReq;
import pl.edu.pja.budget_manager.web.rest.request.UserSignUpReq;
import pl.edu.pja.budget_manager.web.rest.response.TokenRes;
import pl.edu.pja.budget_manager.web.rest.response.UserSignUpRes;

@RestController
@RequestMapping("/api/auth")
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public ResponseEntity<UserSignUpRes> register(@Valid @RequestBody UserSignUpReq userSignUpReq) {
        UserSignUpRes userSignUpRes = authenticationService.signUp(userSignUpReq);

        return ResponseEntity.ok(userSignUpRes);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<TokenRes> authenticate(@Valid @RequestBody UserSignInReq userSignInReq) {
        TokenRes tokenRes = authenticationService.signIn(userSignInReq);

        return ResponseEntity.ok(tokenRes);
    }
}

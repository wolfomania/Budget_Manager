package pl.edu.pja.budget_manager.services;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.pja.budget_manager.domain.Currency;
import pl.edu.pja.budget_manager.domain.User;
import pl.edu.pja.budget_manager.repositories.CurrencyRepository;
import pl.edu.pja.budget_manager.repositories.UserRepository;
import pl.edu.pja.budget_manager.security.JWTService;
import pl.edu.pja.budget_manager.security.UserPrincipal;
import pl.edu.pja.budget_manager.services.mappers.UserMapper;
import pl.edu.pja.budget_manager.services.mappers.UserSignUpReqMapper;
import pl.edu.pja.budget_manager.web.rest.request.UserSignInReq;
import pl.edu.pja.budget_manager.web.rest.request.UserSignUpReq;
import pl.edu.pja.budget_manager.web.rest.response.TokenRes;
import pl.edu.pja.budget_manager.web.rest.response.UserSignUpRes;

import java.util.NoSuchElementException;

@Service
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AuthenticationService {

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    AuthenticationManager authenticationManager;

    CurrencyRepository currencyRepository;

    JWTService jwtService;

    public UserSignUpRes signUp(UserSignUpReq userSignUpReq) {
        Currency currency = currencyRepository.findById(userSignUpReq.getPreferredCurrencyId())
                .orElse(null);

        User newUser = UserSignUpReqMapper.mapToUserSignUpRes(
                userSignUpReq,
                passwordEncoder.encode(userSignUpReq.getPassword()),
                currency
        );

        userRepository.save(newUser);

        return UserMapper.mapToUserSignUpRes(newUser);
    }

    public TokenRes signIn(UserSignInReq userSignInReq) {
        UserPrincipal principal = (UserPrincipal) (authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userSignInReq.getEmail(),
                        userSignInReq.getPassword()
                )
        ).getPrincipal());

        User user = principal.getUser();

//        User user = userRepository.findUserByEmail(userSignInReq.getEmail())
//                .orElseThrow(() -> new NoSuchElementException("User not found"));

        return TokenRes.of(jwtService.generateToken(user), jwtService.getExpirationTime());
    }
}

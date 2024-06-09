package pl.edu.pja.budget_manager.services.mappers;

import pl.edu.pja.budget_manager.domain.Currency;
import pl.edu.pja.budget_manager.domain.User;
import pl.edu.pja.budget_manager.web.rest.request.UserSignUpReq;

import java.time.LocalDateTime;

public class UserSignUpReqMapper {

    public static User mapToUserSignUpRes(UserSignUpReq userSignUpReq, String password, Currency currency) {
        return User.builder()
                .email(userSignUpReq.getEmail())
                .password(password)
                .username(userSignUpReq.getUsername())
                .preferredCurrency(currency)
                .creationDate(LocalDateTime.now())
                .firstName(userSignUpReq.getFirstName())
                .lastName(userSignUpReq.getLastName())
                .build();
    }
}

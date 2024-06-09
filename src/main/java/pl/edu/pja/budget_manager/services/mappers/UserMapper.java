package pl.edu.pja.budget_manager.services.mappers;

import pl.edu.pja.budget_manager.domain.User;
import pl.edu.pja.budget_manager.web.rest.response.UserSignUpRes;

public class UserMapper {

    public static UserSignUpRes mapToUserSignUpRes(User user) {
        return UserSignUpRes.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .preferredCurrency(user.getPreferredCurrency())
                .creationDate(user.getCreationDate())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}

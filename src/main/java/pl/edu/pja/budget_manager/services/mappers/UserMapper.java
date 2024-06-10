package pl.edu.pja.budget_manager.services.mappers;

import pl.edu.pja.budget_manager.domain.Notification;
import pl.edu.pja.budget_manager.domain.User;
import pl.edu.pja.budget_manager.web.rest.response.UserProfileRes;
import pl.edu.pja.budget_manager.web.rest.response.UserSignUpRes;

import java.util.Set;

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

    public static UserProfileRes mapToUserProfileRes(User user, Set<Notification> notifications) {
        return UserProfileRes.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .preferredCurrency(user.getPreferredCurrency())
                .creationDate(user.getCreationDate())
                .notifications(notifications)
                .build();
    }
}

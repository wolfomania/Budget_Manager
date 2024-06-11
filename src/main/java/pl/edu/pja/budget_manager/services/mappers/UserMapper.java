package pl.edu.pja.budget_manager.services.mappers;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import pl.edu.pja.budget_manager.domain.Currency;
import pl.edu.pja.budget_manager.domain.Notification;
import pl.edu.pja.budget_manager.domain.User;
import pl.edu.pja.budget_manager.web.rest.request.UserPatchReq;
import pl.edu.pja.budget_manager.web.rest.response.UserProfileRes;
import pl.edu.pja.budget_manager.web.rest.response.UserSignUpRes;

import java.util.Set;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
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

    public static User mapUserPatchReqToUser(UserPatchReq userPatchReq, User user, Currency currency) {
        /*
        if(userPatchReq.getPassword() != null)
            user.setPassword(userPatchReq.getPassword());

        if(userPatchReq.getUsername() != null)
            user.setUsername(userPatchReq.getUsername());

        if(currency != null)
            user.setPreferredCurrency(currency);

        if(userPatchReq.getFirstName() != null)
            user.setFirstName(userPatchReq.getFirstName());

        if(userPatchReq.getLastName() != null)
            user.setLastName(userPatchReq.getLastName());*/

        return User.builder()
                .email(user.getEmail())
                .password(userPatchReq.getPassword() == null ? user.getPassword() : userPatchReq.getPassword())
                .username(userPatchReq.getUsername() == null ? user.getUsername() : userPatchReq.getUsername())
                .preferredCurrency(currency == null ? user.getPreferredCurrency() : currency)
                .creationDate(user.getCreationDate())
                .firstName(userPatchReq.getFirstName() == null ? user.getFirstName() : userPatchReq.getFirstName())
                .lastName(userPatchReq.getLastName() == null ? user.getLastName() : userPatchReq.getLastName())
                .build();
    }
}

package pl.edu.pja.budget_manager.web.rest.response;

import lombok.Builder;
import lombok.Value;
import pl.edu.pja.budget_manager.domain.Currency;
import pl.edu.pja.budget_manager.domain.Notification;

import java.time.LocalDateTime;
import java.util.Set;

@Value
@Builder
public class UserProfileRes {

    String email;
    String username;
    String firstName;
    String lastName;
    Currency preferredCurrency;
    LocalDateTime creationDate;
    Set<Notification> notifications;

}

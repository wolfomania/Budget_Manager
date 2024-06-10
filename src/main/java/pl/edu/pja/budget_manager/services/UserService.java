package pl.edu.pja.budget_manager.services;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pja.budget_manager.domain.Notification;
import pl.edu.pja.budget_manager.domain.User;
import pl.edu.pja.budget_manager.repositories.NotificationRepository;
import pl.edu.pja.budget_manager.security.UserPrincipal;
import pl.edu.pja.budget_manager.services.mappers.UserMapper;
import pl.edu.pja.budget_manager.web.rest.response.UserProfileRes;

import java.util.Set;

@Service
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserService {

    NotificationRepository notificationRepository;

    @Transactional(readOnly = true)
    public UserProfileRes getUserProfile() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userPrincipal.getUser();
        Set<Notification> notifications = notificationRepository.findAllByUserEmail(user.getEmail());
        return UserMapper.mapToUserProfileRes(user, notifications);
    }
}

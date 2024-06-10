package pl.edu.pja.budget_manager.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pja.budget_manager.domain.User;
import pl.edu.pja.budget_manager.security.UserPrincipal;
import pl.edu.pja.budget_manager.services.UserService;
import pl.edu.pja.budget_manager.services.mappers.UserMapper;
import pl.edu.pja.budget_manager.web.rest.response.UserProfileRes;

@RestController
@RequestMapping("/api/user")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserController {

    UserService userService;

    @GetMapping()
    public ResponseEntity<UserProfileRes> getUserProfile() {
        return ResponseEntity.ok(userService.getUserProfile());
    }

}

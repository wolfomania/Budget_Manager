package pl.edu.pja.budget_manager.web.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.budget_manager.domain.Notification;
import pl.edu.pja.budget_manager.domain.Transaction;
import pl.edu.pja.budget_manager.domain.User;
import pl.edu.pja.budget_manager.security.UserPrincipal;
import pl.edu.pja.budget_manager.services.UserService;
import pl.edu.pja.budget_manager.services.mappers.TransactionMapper;
import pl.edu.pja.budget_manager.services.mappers.UserMapper;
import pl.edu.pja.budget_manager.web.rest.request.AddUserTransactionReq;
import pl.edu.pja.budget_manager.web.rest.request.PatchTransactionReq;
import pl.edu.pja.budget_manager.web.rest.request.UserPatchReq;
import pl.edu.pja.budget_manager.web.rest.response.TransactionRes;
import pl.edu.pja.budget_manager.web.rest.response.UserProfileRes;
import pl.edu.pja.budget_manager.web.rest.response.UserSummaryRes;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

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

    @PatchMapping()
    public ResponseEntity<UserProfileRes> updateUserProfile(@Valid @RequestBody UserPatchReq userPatchReq) {
        return ResponseEntity.ok(userService.updateUserProfile(userPatchReq));
    }

    @GetMapping("/notifications")
    public ResponseEntity<Collection<Notification>> getUserNotifications() {
        return ResponseEntity.ok(userService.getUserNotifications());
    }

    @DeleteMapping("/notifications/{notificationId}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long notificationId) {
        userService.deleteNotification(notificationId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/summary/{startDate}/{endDate}")
    public ResponseEntity<UserSummaryRes> getSummary(Principal principal, @PathVariable LocalDateTime startDate, @PathVariable LocalDateTime endDate) {
        UserSummaryRes summary = userService.getSummary(principal.getName(), startDate, endDate);
        return ResponseEntity.ok(summary);
    }
}

package pl.edu.pja.budget_manager.web.rest.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Getter
@With
public class UserPatchReq {

    @Size(min = 12, message = "User password lengths can not be less than 12")
    String password;

    String username;

    @NonNull
    Long preferredCurrencyId;

    String firstName;

    String lastName;
}

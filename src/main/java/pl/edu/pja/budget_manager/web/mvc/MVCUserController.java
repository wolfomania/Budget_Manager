package pl.edu.pja.budget_manager.web.mvc;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.pja.budget_manager.web.rest.UserController;

@Controller()
@RequestMapping("/user")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class MVCUserController {

    UserController userController;

    @GetMapping
    public String getUserPage(Model model) {
        model.addAttribute("user", userController.getUserProfile().getBody());
        return "user";
    }
}

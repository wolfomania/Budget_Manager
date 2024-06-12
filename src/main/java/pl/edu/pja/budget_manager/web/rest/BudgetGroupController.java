package pl.edu.pja.budget_manager.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pja.budget_manager.services.BudgetGroupService;
import pl.edu.pja.budget_manager.web.rest.response.BudgetGroupPreviewRes;

import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping("/api/user/groups")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class BudgetGroupController {

    BudgetGroupService budgetGroupService;

    @GetMapping
    public Collection<BudgetGroupPreviewRes> getUserGroups(Principal principal) {
        return budgetGroupService.getUserGroups(principal.getName());
    }
}

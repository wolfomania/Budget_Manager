package pl.edu.pja.budget_manager.services;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import pl.edu.pja.budget_manager.domain.BudgetGroup;
import pl.edu.pja.budget_manager.repositories.BudgetGroupRepository;
import pl.edu.pja.budget_manager.services.mappers.BudgetGroupMapper;
import pl.edu.pja.budget_manager.web.rest.response.BudgetGroupPreviewRes;

import java.util.Collection;
import java.util.List;

@Service
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class BudgetGroupService {

    BudgetGroupRepository budgetGroupRepository;

    public List<BudgetGroupPreviewRes> getUserGroups(String name) {
        List<BudgetGroup> budgetGroups= budgetGroupRepository.findAllByUsersEmail(name);
        return BudgetGroupMapper.mapToBudgetGroupPreviewResCollection(budgetGroups);
    }
}

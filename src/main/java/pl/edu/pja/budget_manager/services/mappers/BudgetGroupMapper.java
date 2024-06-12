package pl.edu.pja.budget_manager.services.mappers;

import pl.edu.pja.budget_manager.domain.BudgetGroup;
import pl.edu.pja.budget_manager.web.rest.response.BudgetGroupPreviewRes;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BudgetGroupMapper {

    public static BudgetGroupPreviewRes mapToBudgetGroupPreviewRes(BudgetGroup budgetGroup) {
        return BudgetGroupPreviewRes.builder()
                .creatorEmail(budgetGroup.getCreator().getEmail())
                .groupId(budgetGroup.getGroupId())
                .name(budgetGroup.getName())
                .lastModificationDate(budgetGroup.getLastModificationDate())
                .build();
    }

    public static List<BudgetGroupPreviewRes> mapToBudgetGroupPreviewResCollection(Collection<BudgetGroup> budgetGroups) {
        return budgetGroups.stream()
                .map(BudgetGroupMapper::mapToBudgetGroupPreviewRes)
                .collect(Collectors.toList());
    }
}

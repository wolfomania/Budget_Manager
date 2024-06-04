package pl.edu.pja.budget_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pja.budget_manager.domain.BudgetGroup;

public interface BudgetGroupRepository extends JpaRepository<BudgetGroup, Long> {
}

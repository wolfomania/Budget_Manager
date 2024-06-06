package pl.edu.pja.budget_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pja.budget_manager.domain.BudgetGroup;

import java.util.List;

public interface BudgetGroupRepository extends JpaRepository<BudgetGroup, Long> {

    List<BudgetGroup> findAllByOrderByGroupId();
}

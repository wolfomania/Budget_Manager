package pl.edu.pja.budget_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pja.budget_manager.domain.Debt;
import pl.edu.pja.budget_manager.domain.keys.DebtKey;

public interface DebtRepository extends JpaRepository<Debt, DebtKey> {
}

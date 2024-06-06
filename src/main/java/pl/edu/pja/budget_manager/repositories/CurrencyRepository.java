package pl.edu.pja.budget_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pja.budget_manager.domain.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {

}

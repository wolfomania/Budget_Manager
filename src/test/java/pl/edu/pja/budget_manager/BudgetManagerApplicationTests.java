package pl.edu.pja.budget_manager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.edu.pja.budget_manager.repositories.PeriodicTransactionRepository;

@SpringBootTest
class BudgetManagerApplicationTests {

    @Autowired
    private PeriodicTransactionRepository periodicTransactionRepository;

	@Test
	void contextLoads() {
		periodicTransactionRepository.findPeriodicTransactionsToRenew().forEach(System.out::println);
	}

}

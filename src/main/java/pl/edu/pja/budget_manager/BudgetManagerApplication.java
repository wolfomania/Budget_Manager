package pl.edu.pja.budget_manager;

import jakarta.transaction.Transactional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.annotation.SessionScope;
import pl.edu.pja.budget_manager.domain.BudgetGroup;
import pl.edu.pja.budget_manager.domain.Debt;
import pl.edu.pja.budget_manager.domain.GroupApplication;
import pl.edu.pja.budget_manager.domain.User;
import pl.edu.pja.budget_manager.domain.keys.GroupTransactionKey;
import pl.edu.pja.budget_manager.domain.keys.UserGroupKey;
import pl.edu.pja.budget_manager.repositories.BudgetGroupRepository;
import pl.edu.pja.budget_manager.repositories.GroupApplicationRepository;
import pl.edu.pja.budget_manager.repositories.UserRepository;

import java.time.LocalDateTime;

@SpringBootApplication
public class BudgetManagerApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(BudgetManagerApplication.class, args);
//		BudgetManagerApplication app = context.getBean(BudgetManagerApplication.class);
//		app.test(context);

	}

	@Transactional
    void test(ConfigurableApplicationContext context) {
		UserRepository userRepository = context.getBean(UserRepository.class);
		BudgetGroupRepository budgetGroupRepository = context.getBean(BudgetGroupRepository.class);
		GroupApplicationRepository groupApplicationRepository = context.getBean(GroupApplicationRepository.class);
		BudgetGroup budgetGroup = budgetGroupRepository.getReferenceById(1L);
		User user = userRepository.getReferenceById("andrii");
		GroupApplication groupApplication = groupApplicationRepository.getReferenceById(new UserGroupKey(user.getEmail(), budgetGroup.getGroupId()));
		System.out.println(groupApplication);
	}

}

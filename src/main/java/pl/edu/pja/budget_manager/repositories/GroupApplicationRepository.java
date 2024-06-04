package pl.edu.pja.budget_manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pja.budget_manager.domain.GroupApplication;
import pl.edu.pja.budget_manager.domain.keys.UserGroupKey;

public interface GroupApplicationRepository extends JpaRepository<GroupApplication, UserGroupKey> {
}

package pl.edu.pja.budget_manager.web.rest.response;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class BudgetGroupPreviewRes {

    Long groupId;

    String name;

    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime lastModificationDate;

    String creatorEmail;
}

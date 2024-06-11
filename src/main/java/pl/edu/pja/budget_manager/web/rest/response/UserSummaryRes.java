package pl.edu.pja.budget_manager.web.rest.response;

import lombok.Value;

import java.util.Map;

@Value(staticConstructor = "of")
public class UserSummaryRes {

    Map<String, Double> summary;

}

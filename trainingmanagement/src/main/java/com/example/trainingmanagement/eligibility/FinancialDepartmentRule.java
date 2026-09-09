package com.example.trainingmanagement.eligibility;

import com.example.trainingmanagement.model.Nomination;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FinancialDepartmentRule
        implements EligibilityRule {

    private final List<String> allowedDepartments =
            List.of(
                    "FINANCE",
                    "BUDGET",
                    "PLANNING"
            );

    @Override
    public boolean appliesTo(Nomination nomination) {

        return Long.valueOf(1L)
        .equals(nomination.getTrainingProgramId());
    }

    @Override
    public boolean isEligible(Nomination nomination) {

        if (nomination.getDepartmentName() == null) {
            return false;
        }

        return allowedDepartments.contains(
                nomination.getDepartmentName()
                        .trim()
                        .toUpperCase()
        );
    }

    @Override
    public String getFailureMessage() {

        return "Financial Management Programme is only for Finance, Budget or Planning officers.";
    }
}
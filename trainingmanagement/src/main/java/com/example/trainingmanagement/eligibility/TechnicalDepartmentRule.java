package com.example.trainingmanagement.eligibility;

import com.example.trainingmanagement.model.Nomination;
import org.springframework.stereotype.Component;

@Component
public class TechnicalDepartmentRule
        implements EligibilityRule {

    @Override
    public boolean appliesTo(Nomination nomination) {
        return Long.valueOf(2L)
                .equals(nomination.getTrainingProgramId());
    }

    @Override
    public boolean isEligible(Nomination nomination) {

        if (nomination.getDepartmentName() == null) {
            return false;
        }

        String department =
                nomination.getDepartmentName()
                        .trim()
                        .toUpperCase();

        return department.equals("IT")
                || department.contains("ICT");
    }

    @Override
    public String getFailureMessage() {
        return "Technical Programme is only for IT or ICT-related officers.";
    }
}
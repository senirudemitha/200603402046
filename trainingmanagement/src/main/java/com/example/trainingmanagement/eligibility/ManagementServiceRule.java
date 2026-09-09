package com.example.trainingmanagement.eligibility;

import com.example.trainingmanagement.model.Nomination;
import org.springframework.stereotype.Component;

@Component
public class ManagementServiceRule
        implements EligibilityRule {

    @Override
    public boolean appliesTo(Nomination nomination) {

        return Long.valueOf(3L)
                .equals(nomination.getTrainingProgramId());
    }

    @Override
    public boolean isEligible(Nomination nomination) {

        return nomination.getYearsOfService() != null
                && nomination.getYearsOfService() >= 5;
    }

    @Override
    public String getFailureMessage() {

        return "Management Development Programme requires at least 5 years of service.";
    }
}
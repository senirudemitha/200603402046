package com.example.trainingmanagement.service;

import com.example.trainingmanagement.eligibility.EligibilityRule;
import com.example.trainingmanagement.model.Nomination;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EligibilityService {

    private final List<EligibilityRule> rules;

    public EligibilityService(List<EligibilityRule> rules) {
        this.rules = rules;
    }

    public void checkEligibility(Nomination nomination) {

        for (EligibilityRule rule : rules) {

            if (rule.appliesTo(nomination)
                    && !rule.isEligible(nomination)) {

                throw new IllegalArgumentException(
                        "Not eligible: "
                                + rule.getFailureMessage()
                );
            }
        }
    }
}
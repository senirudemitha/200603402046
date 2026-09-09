package com.example.trainingmanagement.eligibility;

import com.example.trainingmanagement.model.Nomination;

public interface EligibilityRule {

    boolean appliesTo(Nomination nomination);

    boolean isEligible(Nomination nomination);

    String getFailureMessage();
}
package com.example.trainingmanagement.eligibility;

import com.example.trainingmanagement.model.Nomination;
import com.example.trainingmanagement.repository.TrainingHistoryRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PreviousParticipationRule
        implements EligibilityRule {

    private final TrainingHistoryRepository historyRepository;

    public PreviousParticipationRule(
            TrainingHistoryRepository historyRepository) {

        this.historyRepository = historyRepository;
    }

    @Override
    public boolean appliesTo(Nomination nomination) {
        return true;
    }

    @Override
    public boolean isEligible(Nomination nomination) {

        LocalDate twelveMonthsAgo =
                LocalDate.now().minusMonths(12);

        boolean participatedRecently =
                historyRepository
                        .existsByTrainingProgramIdAndOfficerIdAndParticipationDateAfter(
                                nomination.getTrainingProgramId(),
                                nomination.getOfficerId(),
                                twelveMonthsAgo
                        );

        return !participatedRecently;
    }

    @Override
    public String getFailureMessage() {

        return "Officer participated in the same training programme within the previous 12 months.";
    }
}
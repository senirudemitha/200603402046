package com.example.trainingmanagement.repository;

import com.example.trainingmanagement.model.TrainingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface TrainingHistoryRepository
        extends JpaRepository<TrainingHistory, Long> {

    boolean existsByTrainingProgramIdAndOfficerIdAndParticipationDateAfter(
            Long trainingProgramId,
            Long officerId,
            LocalDate participationDate
    );
}
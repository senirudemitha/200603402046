package com.example.trainingmanagement.repository;

import com.example.trainingmanagement.model.Nomination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NominationRepository
        extends JpaRepository<Nomination, Long> {

    boolean existsByTrainingProgramIdAndOfficerId(
            Long trainingProgramId,
            Long officerId
    );
}
package com.example.trainingmanagement.repository;

import com.example.trainingmanagement.model.Nomination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NominationRepository
        extends JpaRepository<Nomination, Long> {

    boolean existsByTrainingProgramIdAndOfficerId(
            Long trainingProgramId,
            Long officerId
    );

    long countByTrainingProgramIdAndStatus(
            Long trainingProgramId,
            String status
    );

    Optional<Nomination>
    findFirstByTrainingProgramIdAndStatusOrderByIdAsc(
            Long trainingProgramId,
            String status
    );

    List<Nomination>
    findByTrainingProgramIdOrderByIdAsc(
            Long trainingProgramId
    );
}
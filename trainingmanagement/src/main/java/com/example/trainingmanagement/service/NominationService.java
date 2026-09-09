package com.example.trainingmanagement.service;

import com.example.trainingmanagement.model.Nomination;
import com.example.trainingmanagement.repository.NominationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NominationService {

    private final NominationRepository repository;
    private final EligibilityService eligibilityService;

    public NominationService(
            NominationRepository repository,
            EligibilityService eligibilityService) {

        this.repository = repository;
        this.eligibilityService = eligibilityService;
    }

    public Nomination create(Nomination nomination) {

        // TASK 1 - Duplicate prevention
        boolean duplicate =
                repository.existsByTrainingProgramIdAndOfficerId(
                        nomination.getTrainingProgramId(),
                        nomination.getOfficerId()
                );

        if (duplicate) {
            throw new IllegalArgumentException(
                    "Duplicate nomination: officer already nominated for this training"
            );
        }


        // TASK 3 - Eligibility checking
        eligibilityService.checkEligibility(nomination);


        // TASK 2 - Capacity checking
        long confirmedCount =
                repository.countByTrainingProgramIdAndStatus(
                        nomination.getTrainingProgramId(),
                        "CONFIRMED"
                );

        if (confirmedCount < nomination.getMaximumCapacity()) {
            nomination.setStatus("CONFIRMED");
        } else {
            nomination.setStatus("WAITING");
        }

        return repository.save(nomination);
    }

    public List<Nomination> getAll() {
        return repository.findAll();
    }

    public List<Nomination> getByTraining(Long trainingProgramId) {

        return repository.findByTrainingProgramIdOrderByIdAsc(
                trainingProgramId
        );
    }

    public void cancel(Long id) {

        Optional<Nomination> optionalNomination =
                repository.findById(id);

        if (optionalNomination.isEmpty()) {
            throw new IllegalArgumentException(
                    "Nomination not found"
            );
        }

        Nomination nomination =
                optionalNomination.get();

        boolean wasConfirmed =
                "CONFIRMED".equals(
                        nomination.getStatus()
                );

        Long trainingProgramId =
                nomination.getTrainingProgramId();

        repository.delete(nomination);


        // TASK 2 - Promote first waiting officer
        if (wasConfirmed) {

            Optional<Nomination> firstWaiting =
                    repository
                            .findFirstByTrainingProgramIdAndStatusOrderByIdAsc(
                                    trainingProgramId,
                                    "WAITING"
                            );

            if (firstWaiting.isPresent()) {

                Nomination promoted =
                        firstWaiting.get();

                promoted.setStatus("CONFIRMED");

                repository.save(promoted);
            }
        }
    }
}
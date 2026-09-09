package com.example.trainingmanagement.service;

import com.example.trainingmanagement.model.Nomination;
import com.example.trainingmanagement.repository.NominationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NominationService {

    private final NominationRepository repository;

    public NominationService(NominationRepository repository) {
        this.repository = repository;
    }

    public Nomination create(Nomination nomination) {

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

        Nomination nomination = optionalNomination.get();

        boolean wasConfirmed =
                "CONFIRMED".equals(nomination.getStatus());

        Long trainingProgramId =
                nomination.getTrainingProgramId();

        repository.delete(nomination);

        if (wasConfirmed) {

            Optional<Nomination> firstWaiting =
                    repository
                            .findFirstByTrainingProgramIdAndStatusOrderByIdAsc(
                                    trainingProgramId,
                                    "WAITING"
                            );

            if (firstWaiting.isPresent()) {
                Nomination promoted = firstWaiting.get();
                promoted.setStatus("CONFIRMED");
                repository.save(promoted);
            }
        }
    }
}
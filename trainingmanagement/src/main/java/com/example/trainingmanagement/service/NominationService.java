package com.example.trainingmanagement.service;

import com.example.trainingmanagement.model.Nomination;
import com.example.trainingmanagement.repository.NominationRepository;
import org.springframework.stereotype.Service;

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
                        nomination.getOfficerId());

        if (duplicate) {
            throw new IllegalArgumentException(
                    "Duplicate nomination: officer already nominated for this training");
        }

        return repository.save(nomination);
    }
    public java.util.List<Nomination> getAll() {
    return repository.findAll();
}
}
package com.example.trainingmanagement.controller;

import com.example.trainingmanagement.model.TrainingHistory;
import com.example.trainingmanagement.repository.TrainingHistoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/training-history")
public class TrainingHistoryController {

    private final TrainingHistoryRepository repository;

    public TrainingHistoryController(
            TrainingHistoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<TrainingHistory> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> add(
            @RequestBody TrainingHistory history) {

        if (history.getTrainingProgramId() == null ||
                history.getOfficerId() == null ||
                history.getParticipationDate() == null) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            "Please provide training, officer and participation date."
                    );
        }

        return ResponseEntity.ok(
                repository.save(history)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id) {

        if (!repository.existsById(id)) {

            return ResponseEntity
                    .badRequest()
                    .body(
                            "Participation record not found."
                    );
        }

        repository.deleteById(id);

        return ResponseEntity.ok(
                "Participation record deleted."
        );
    }
}
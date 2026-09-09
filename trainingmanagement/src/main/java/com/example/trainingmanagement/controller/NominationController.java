package com.example.trainingmanagement.controller;

import com.example.trainingmanagement.model.Nomination;
import com.example.trainingmanagement.service.NominationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nominations")
public class NominationController {

    private final NominationService service;

    public NominationController(NominationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody Nomination nomination) {

        try {
            return ResponseEntity.ok(
                    service.create(nomination)
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(
                service.getAll()
        );
    }

    @GetMapping("/training/{trainingProgramId}")
    public ResponseEntity<?> getByTraining(
            @PathVariable Long trainingProgramId) {

        return ResponseEntity.ok(
                service.getByTraining(trainingProgramId)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancel(
            @PathVariable Long id) {

        try {
            service.cancel(id);
            return ResponseEntity.ok(
                    "Nomination cancelled"
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
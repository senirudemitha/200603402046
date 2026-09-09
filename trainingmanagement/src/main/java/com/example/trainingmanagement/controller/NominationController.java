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
    public ResponseEntity<?> create(@RequestBody Nomination nomination) {
        try {
            return ResponseEntity.ok(service.create(nomination));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
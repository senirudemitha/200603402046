package com.example.trainingmanagement.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "training_history")
public class TrainingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long trainingProgramId;

    private Long officerId;

    private LocalDate participationDate;

    public TrainingHistory() {
    }

    public Long getId() {
        return id;
    }

    public Long getTrainingProgramId() {
        return trainingProgramId;
    }

    public void setTrainingProgramId(Long trainingProgramId) {
        this.trainingProgramId = trainingProgramId;
    }

    public Long getOfficerId() {
        return officerId;
    }

    public void setOfficerId(Long officerId) {
        this.officerId = officerId;
    }

    public LocalDate getParticipationDate() {
        return participationDate;
    }

    public void setParticipationDate(LocalDate participationDate) {
        this.participationDate = participationDate;
    }
}   
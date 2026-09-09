package com.example.trainingmanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "nominations",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"training_program_id", "officer_id"}))
public class Nomination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "training_program_id", nullable = false)
    private Long trainingProgramId;

    @Column(name = "officer_id", nullable = false)
    private Long officerId;

    private Long departmentId;

    public Nomination() {}

    public Long getId() { return id; }

    public Long getTrainingProgramId() { return trainingProgramId; }
    public void setTrainingProgramId(Long trainingProgramId) {
        this.trainingProgramId = trainingProgramId;
    }

    public Long getOfficerId() { return officerId; }
    public void setOfficerId(Long officerId) {
        this.officerId = officerId;
    }

    public Long getDepartmentId() { return departmentId; }
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
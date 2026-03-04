package com.cts.patient_registry_service.entity;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;

@Entity

@Table(name = "problem")

@Data

@NoArgsConstructor

@AllArgsConstructor

@Builder

public class Problem {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "problem_id")

    private Long problemId;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "patient_id", nullable = false)

    private Patient patient;

    @Column(name = "code", nullable = false, length = 20)

    private String code;

    @Column(name = "description", length = 255)

    private String description;

    @Column(name = "onset_date")

    private LocalDate onsetDate;

    @Enumerated(EnumType.STRING)

    @Column(name = "status", nullable = false)

    private ProblemStatus status;

    public enum ProblemStatus {

        ACTIVE, RESOLVED

    }

}

package com.cts.appointment_service.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "encounter")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Encounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long encounterId;

    @Column(nullable = false)
    private Long patientId;

    @Column(nullable = false)
    private Long providerId;

    @Column(nullable = false)
    private LocalDateTime encounterDateTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EncounterType type;

    @Column
    private String diagnosisCode;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EncounterStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = EncounterStatus.OPEN;
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum EncounterType {
        OPD, IPD, TELEHEALTH
    }

    public enum EncounterStatus {
        OPEN, CLOSED
    }
}

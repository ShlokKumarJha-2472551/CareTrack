package com.cts.appointment_service.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointment")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @Column(nullable = false)
    private Long patientId;

    @Column(nullable = false)
    private Long providerId;

    @Column(nullable = false)
    private LocalDateTime scheduledDateTime;

    @Column
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AppointmentStatus status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = AppointmentStatus.SCHEDULED;
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum AppointmentStatus {
        SCHEDULED, CHECKED_IN,
        COMPLETED, CANCELLED, NO_SHOW
    }
}

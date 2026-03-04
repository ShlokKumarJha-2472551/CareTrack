package com.cts.patient_registry_service.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "patient")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long patientId;
    @Column(name = "mrn", nullable = false,
            unique = true, length = 20)
    private String mrn;
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "dob", nullable = false)
    private LocalDate dob;
    @Column(name = "gender", nullable = false, length = 10)
    private String gender;
    @Column(name = "contact_info", length = 255)
    private String contactInfo;
    @Column(name = "primary_provider_id")
    private Long primaryProviderId;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PatientStatus status;
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "patient",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Allergy> allergies;
    @OneToMany(mappedBy = "patient",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Problem> problems;
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    public enum PatientStatus {
        ACTIVE, INACTIVE
    }
}
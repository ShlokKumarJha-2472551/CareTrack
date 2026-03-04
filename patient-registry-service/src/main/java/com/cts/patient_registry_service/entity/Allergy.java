package com.cts.patient_registry_service.entity;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;

@Entity

@Table(name = "allergy")

@Data

@NoArgsConstructor

@AllArgsConstructor

@Builder

public class Allergy {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "allergy_id")

    private Long allergyId;

    @ManyToOne(fetch = FetchType.LAZY)

    @JoinColumn(name = "patient_id", nullable = false)

    private Patient patient;

    @Column(name = "substance", nullable = false, length = 100)

    private String substance;

    @Column(name = "reaction", length = 255)

    private String reaction;

    @Column(name = "severity", length = 20)

    private String severity;

    @Column(name = "noted_date")

    private LocalDate notedDate;

}

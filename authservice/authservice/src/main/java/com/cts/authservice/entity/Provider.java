package com.cts.authservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name = "providers")
@Setter
@NoArgsConstructor
public class Provider {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long providerId;

    @Column(nullable = false)
    private String speciality;

    @Column(nullable = false, unique = true)
    private String licenceNumber;

    private String department;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

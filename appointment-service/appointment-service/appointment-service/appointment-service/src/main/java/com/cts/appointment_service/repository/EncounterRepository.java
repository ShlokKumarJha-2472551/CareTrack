package com.cts.appointment_service.repository;

import com.cts.appointment_service.entity.Encounter;
import com.cts.appointment_service.entity.Encounter.EncounterStatus;
import com.cts.appointment_service.entity.Encounter.EncounterType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EncounterRepository
        extends JpaRepository<Encounter, Long> {

    List<Encounter> findByPatientId(Long patientId);
    List<Encounter> findByProviderId(Long providerId);
    List<Encounter> findByStatus(EncounterStatus status);
    List<Encounter> findByType(EncounterType type);
    List<Encounter> findByPatientIdAndStatus(
            Long patientId, EncounterStatus status);
}
